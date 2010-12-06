/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connectivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import org.w3c.dom.Node;
import settings.Settings;

/**
 *  SOAP Interface class
 *
 *  Acts as an interface between the rest of our application on one hand and
 *  the MaNGOS server on the other, using SOAP messages to communicate.
 *
 *  @author Darkrulerz
 */
public class SoapManager {

    private SOAPConnection      connection      =   null;
    private URL                 destination     =   null;


    /**
     *  SOAP Interface constructor. Initializes the connection and destination
     *  nodes, required to send messages.
     */
    public SoapManager () throws SOAPException, MalformedURLException {
        createConnection();
        createDestination();
    }

    /**
     *  Public 'write' command - allows external objects to send a message
     *  to the server (through the SOAP interface) and receive the reply.
     *
     *  @param command      The command to send to the server.
     *  @return             The result string, if any (note, certain commands do NOT
     *                      yield a response string! This will be empty in that case).
     */
    public String write (String command) throws IOException, SOAPException {
        String result   =   "";
        SOAPMessage message     =   this.getCommandMessage(command);
        SOAPMessage reply       =   connection.call(message, destination);
        SOAPBody    body        =   reply.getSOAPBody();
        Node        content     =   body.getFirstChild().getFirstChild();
        result                  =   content.getTextContent();
        return result;
    }

    /**
     *  Create a destination URL object.
     *
     *  This serves as the endpoint for our SOAP connection - a location to
     *  connect to. Since we need to add authentication this becomes a fairly
     *  long URI.
     *
     *  @throws MalformedURLException
     */
    private void createDestination () throws MalformedURLException {
        if (this.destination != null)
            return;
        String  target  = "http://" + Settings.get("mangos", "gmUser")
                                    + ":"
                                    + Settings.get("mangos", "gmPassword")
                                    + "@"
                                    + Settings.get("mangos", "remoteAddress")
                                    + ":"
                                    + Settings.get("mangos", "soapPort");
        this.destination = new URL(target);
    }

    /**
     *  Create the soap connection we will use to call an remote procedure with.
     *
     *  Note that at this point it is an empty connection - it will require an
     *  URL endpoint to actually connect to anything.
     *
     *  @throws UnsupportedOperationException
     *  @throws SOAPException
     */
    private void createConnection () throws UnsupportedOperationException,
                                            SOAPException {
        if (this.connection != null)
            return;
        SOAPConnectionFactory factory   = SOAPConnectionFactory.newInstance();
        this.connection                 = factory.createConnection();
    }

    /**
     *  Create a SOAP executeCommand message
     *
     *  This is the basic SOAPMessage required to build command requests with.
     *
     *  @param  command     The command to execute on the server.
     *  @return             The SOAPmessage object used to call that command.
     *  @throws SOAPException
     *  @throws IOException
     */
    private SOAPMessage getCommandMessage (String command) throws   SOAPException,
                                                                    IOException {
          QName         bodyName    =   new QName("ns1:executeCommand");
          QName         commandName =   new QName("command");
          SOAPMessage   message     =   MessageFactory.newInstance().createMessage();

          message.getSOAPBody().addBodyElement(bodyName).addChildElement(commandName).addTextNode(command);
          message.getSOAPPart().getEnvelope().setAttribute("xmlns:ns1", "urn:MaNGOS");

          return message;
    }
}