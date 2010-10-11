/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgets;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Koen Bollen, 2010
 */
public class VLC extends Thread
{
    public class Controller
    {
        private VLC rc;

        private Controller( VLC rc )
        {
            this.rc = rc;
        }

        /**
         * Start playback.
         *
         * @return
         */
        public int play()
        {
            return rc.execute( "play" );
        }

        /**
         * Toggle playback between pause and play.
         * @return
         */
        public int pause()
        {
            return rc.execute( "pause" );
        }

        /**
         * Stop playback.
         *
         * @return
         */
        public int stop()
        {
            return rc.execute( "stop" );
        }

        /**
         * Set VLC to fullscreen.
         */
        public void fullscreen()
        {
            rc.command( "f on" );
        }

        /**
         * Clear the current playlist.
         *
         * @return 0 on success
         */
        public int clear()
        {
            return rc.execute( "clear" );
        }

        /**
         * Add a file to the playlist.
         *
         * @param path The path/filename to add.
         * @return 0 on success.
         */
        public int add( String path )
        {
            return rc.execute( "add " + path.trim() );
        }

        /**
         * Enqueue a file to the playlist.
         *
         * @param path the path/filename of to enque.
         * @return 0 on success.
         */
        public int enqueue( String path )
        {
            return rc.execute( "enqueue " + path.trim() );
        }

        /**
         * Request all statuses, they are send to the {StatusChangeListener}s.
         */
        public void status()
        {
            rc.command( "status" );
        }
    }

    public interface StatusChangeListener
    {
        /**
         * This method is called when VLC reports a 'status change'. Add an
         * instance of the extending class is to this VLC remotecontrol class.
         *
         * @param rc The parent VLC instance.
         * @param name The name of the status property.
         * @param value The new value of this status property.
         * @param message The message on this change.
         */
        public void onStatusChanged( VLC rc, String name, String value, String message );

    }

    public class Information
    {
        private VLC rc;

        private Information( VLC rc )
        {
            this.rc = rc;
        }

        private int getInt( String command )
        {
            synchronized(rc.output)
            {
                rc.output.clear();
                rc.command( command );
                int value = -1;
                try
                {
                    String result = rc.output.poll(rc.timeout, TimeUnit.MILLISECONDS);
                    value = Integer.parseInt( result );
                } catch (InterruptedException ex)
                {
                    value = -1;
                } catch( NumberFormatException ex)
                {
                    value = -1;
                }
                return value;
            }
        }

        private String getString( String command )
        {
            synchronized(rc.output)
            {
                rc.output.clear();
                rc.command( command );
                try
                {
                    return rc.output.poll(rc.timeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex)
                {
                    return null;
                }
            }
        }


        /**
         * Return the current time of the playing item.
         *
         * @return The time.
         */
        public int getTime()
        {
            return getInt( "get_time" );
        }

        /**
         * Return the total length of the currently playing item.
         */
        public int getLength()
        {
            return getInt( "get_time" );
        }

        /**
         * Return the title/filename of the current item.
         *
         * @return
         */
        public String getTitle()
        {
            return getString( "get_title" );
        }
    }

    public static final Pattern rx_return = Pattern.compile("(\\w+): returned (\\d+) \\((.+)\\)");
    public static final Pattern rx_status = Pattern.compile("status change: \\((.+?): (.+?)\\)(:(.*))?");


    /**
     * The controller interface to the VLC client.
     */
    public final Controller control;

    /**
     * The information interface from the VLC client.
     */
    public final Information info;

    protected String hostname;
    protected int port;

    protected Socket sock;
    protected BufferedReader in;
    protected BufferedOutputStream out;

    private boolean connected;

    private boolean verbose;
    private long timeout;

    private Map<String, LinkedBlockingQueue<Integer>> returns;
    private LinkedBlockingQueue<String> output;
    private List<StatusChangeListener> listeners;

    /**
     * The VLC constructor. Also takes the hostname and port paramenters.
     *
     * @param hostname The hostname to connect to.
     * @param port The port number to connect on.
     * @param verbose If true, print out transfer data.
     */
    public VLC( String hostname, int port, boolean verbose )
    {
        this.hostname = hostname;
        this.port = port;

        this.connected = false;
        this.verbose = verbose;
        this.timeout = 1000;

        this.listeners = new LinkedList<StatusChangeListener>();
        this.returns = new HashMap<String, LinkedBlockingQueue<Integer>>();
        this.output = new LinkedBlockingQueue<String>();

        this.control = new Controller( this );
        this.info = new Information( this );

    }

    /**
     * Constructor where verbose is default false.
     *
     * @param hostname The hostname to connect to.
     * @param port The port number to connect on.
     */
    public VLC( String hostname, int port )
    {
        this( hostname, port, false );
    }

    /**
     * The start method, this method does the real connecting and starts the
     * receive thread. This needs to be called first.
     */
    @Override
    public void start()
    {
        if( this.connected )
        {
            System.out.println( "Already connected!" );
            return;
        }

        // Connect:
        try
        {
            this.sock = new Socket(this.hostname, this.port);
            this.in = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            this.out = new BufferedOutputStream(this.sock.getOutputStream());
        } catch (IOException ex)
        {
            System.err.println( "Unable to connect to VLC RC: " + ex.getMessage() );
            System.exit(1);
        }

        this.connected = true;

        // Start thread:
        super.start();
    }

    @Override
    public void run()
    {
        if( !this.connected )
        {
            System.err.println( "VLC is not connected, please call start() first!" );
            System.exit( 1 );
        }

        while( this.sock.isConnected() )
        {
            String line = null;

            try
            {
                line = this.in.readLine();
            } catch( IOException e )
            {
                continue;
            }

            if( line == null )
                continue;

            if( this.verbose )
                System.out.println( "< " + line.trim() );

            Matcher matcher = rx_return.matcher( line.trim() );
            if( matcher.find() )
            {
                String command = matcher.group(1);
                int result = Integer.parseInt(matcher.group(2));
                Queue<Integer> q = this.returns.get(command);
                q.offer( result );
                continue;
            }

            matcher = rx_status.matcher( line.trim() );
            if( matcher.find() )
            {
                String name = matcher.group( 1 ).trim();
                String value = matcher.group( 2 ).trim();
                String message = matcher.group(3);
                if( message != null )
                    message = message.trim();
                for( StatusChangeListener l : this.listeners )
                    l.onStatusChanged(this, name, value, message);
                continue;
            }

            output.offer(line.trim());
        }
    }

    public void addStatusChangeListener( StatusChangeListener listener )
    {
        this.listeners.add( listener );
    }

    private void send_command( String command ) throws IOException
    {
        if( !this.connected )
        {
            System.err.println( "VLC is not connected, please call start() first!" );
            System.exit( 1 );
        }

        String data = command.trim() + "\n";
        if (this.verbose)
            System.out.println("> " + data.trim());
        this.out.write(data.getBytes(), 0, data.length());
        this.out.flush();
    }

    public void command( String command )
    {
        try
        {
            send_command( command );
        } catch (IOException ex)
        {
            System.err.println( "Unable to send command '" + command + "': " + ex.getMessage() );
        }
    }

    public int execute( String command )
    {
        String[] split = command.split("\\s+");
        String name = split[0];
        if( !returns.containsKey(name) )
            returns.put(name, new LinkedBlockingQueue<Integer>());
        try
        {
            send_command(command);
        } catch (IOException ex)
        {
            return -1;
        }
        Integer result;
        try
        {
            result = returns.get(name).poll(this.timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex)
        {
            return -1;
        }
        if( result == null )
            return -1;
        return result;
    }

    public void setTimeout( long timeout )
    {
        this.timeout = timeout;
    }

}
