/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package distance;

/**
 *
 * @author Dark
 */
public class Address
{
    String Address;
    double Latitude;
    double Longitude;
    Address(String address, double latitude, double longitude)
    {
        this.Address = address;
        this.Latitude = latitude;
        this.Longitude = longitude;
    }
    public void Print(Address ... location)
    {
        System.out.println("From "+location[0].Address+"\n  Latitude:  " + location[0].Latitude + "  Longitude: " + location[0].Longitude);
        for (int i = 1; i < location.length; i++)
        {
            System.out.println("to "+location[i].Address+"\n  Latitude:  " + location[i].Latitude + "  Longitude: " + location[i].Longitude);
        }
    }
    public int CalcDistance(Address ... location)
    {
        // some complicated calculation
        // do not try to understand this :)
        double distance, meanRadius, dLat, dLon, a, c;
        distance = 0;
        for (int i = 0; i < location.length; i++)
        {
            if (i+1 >= location.length)
                break;
            else            // hoeft in principe niet in een else... maar het staat mooier
            {
                meanRadius = 6371; //km
                dLat = Math.toRadians(location[i].Latitude - location[i+1].Latitude);
                dLon = Math.toRadians(location[i].Longitude - location[i+1].Longitude);
                a = Math.sin(dLat / 2)
                        * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(location[i].Latitude))
                        * Math.cos(Math.toRadians(location[i+1].Latitude))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);
                c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                distance += meanRadius * c;
            }
        }
        return (int)distance;
    }
}