package orders;

import java.util.*;
import java.util.Map.Entry;



/**
 * <p> Application to help employee to order meals.  
 * This system depends on a functionality that collects
 * employees choices of restaurants and stores mappings 
 * between the restaurant names and their employee IDs.</p>
 *
 * @author Santosh Dubey
 */
public class MealReservationApplication
{
    //~ Inner Classes ----------------------------------------------------------

    public static class MealReservation
    {
        //~ Constructors -------------------------------------------------------

    	   	
        public MealReservation( List<List<String>> orders )
        {
            if ( !orders.isEmpty( ) )
            {
                for ( List<String> orderList : orders )
                {
                    String restaurantName = orderList.get( 0 );
                    String employeeID = orderList.get( 1 );

                    if ( !orderPlacedMap.containsKey( restaurantName ) )
                    {	
                        orderPlacedMap.put( restaurantName, new ArrayList<String>( ) );
                        orderPlacedMap.get( restaurantName ).add( employeeID );
                    }
                    else
                    {
                        orderPlacedMap.get( restaurantName ).add( employeeID );
                    }
                }
            }
        }

        //~ Methods ------------------------------------------------------------

        public List<String> getEmployeeIdsOrderedFromRestaurant(
            String restaurant )
        {
            List<String> employeeList = new ArrayList<String>( );

            if ( orderPlacedMap.containsKey( restaurant ) )
            {
                employeeList = orderPlacedMap.get( restaurant );
            }

            return employeeList;
        }

        public Map<String, List<String>> listOrders( )
        {
            return orderPlacedMap;
        }


        public void addOrders( List<List<String>> orders )
        {
            if ( !orders.isEmpty( ) )
            {
                for ( List<String> orderList : orders )
                {
                    String restaurantName = orderList.get( 0 );
                    String employeeID = orderList.get( 1 );

                    if ( !orderPlacedMap.containsKey( restaurantName ) )
                    {
                        orderPlacedMap.put( restaurantName, new ArrayList<String>( ) );

                        orderPlacedMap.get( restaurantName ).add( employeeID );
                    }
                    else
                    {
                        orderPlacedMap.get( restaurantName ).add( employeeID );
                    }
                }
            }
        }


        public void removeOrders( List<List<String>> orders )
        {
            if ( !orders.isEmpty( ) )
            {
                for ( List<String> temp : orders )
                {
                    String restaurantName = temp.get( 0 );
                    String employeeID = temp.get( 1 );

                    if ( orderPlacedMap.containsKey( restaurantName ) )
                    {
                        orderPlacedMap.get( restaurantName ).remove( employeeID );

                        if ( orderPlacedMap.get( restaurantName ).size( ) == 0 )
                        {
                            orderPlacedMap.remove( restaurantName );
                        }
                    }
                }
            }
        }


        public int findNumberOfOrders( )
        {
            int numOfOrders = 0;

            for ( Entry<String, List<String>> orderPlacedMapEntry : orderPlacedMap.entrySet( ) )
            {
                numOfOrders += orderPlacedMapEntry.getValue( ).size( );
            }

            return numOfOrders;
        }


        public List<String> findMostOrderedRestaurants( )
        {
            int prevSize = 0;
            List<String> restaurantNameList = new ArrayList<String>( );

            for ( Entry<String, List<String>> orderPlacedMapEntry : orderPlacedMap.entrySet( ) )
            {
                int currSize = orderPlacedMapEntry.getValue( ).size( );

                if ( currSize > prevSize )
                {
                    String restaurantName = orderPlacedMapEntry.getKey( );
                    restaurantNameList = new ArrayList<String>( );
                    restaurantNameList.add( restaurantName );
                    prevSize = currSize;
                }

                if ( currSize == prevSize )
                {
                    String restaurantName = orderPlacedMapEntry.getKey( );

                    if ( !restaurantNameList.contains( restaurantName ) )
                    {
                        restaurantNameList.add( restaurantName );
                    }

                    prevSize = currSize;
                }
            }

            return restaurantNameList;
        }

        //~ Static variables ---------------------------------------------------

        private static Map<String, List<String>> orderPlacedMap =
            new HashMap<String, List<String>>( );
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * @author Santosh Dubey
    *
    * @param args
    */
    public static void main( String[] args )
    {
        // Initial reservation of 3 orders.
        MealReservation reservation =
            new MealReservation( new ArrayList<List<String>>(
                    Arrays.asList( Arrays.asList( "Panda Express", "3" ),
                        Arrays.asList( "KFC", "1" ),
                        Arrays.asList( "Sichuan Gourmet", "6" ) ) ) );

        // Adding 3 orders.
        reservation.addOrders( new ArrayList<List<String>>(
                Arrays.asList( Arrays.asList( "Sichuan Gourmet", "2" ),
                    Arrays.asList( "Sichuan Gourmet", "5" ),
                    Arrays.asList( "Panda Express", "4" ) ) ) );

        // Adding 3 spurious orders
        reservation.addOrders( new ArrayList<List<String>>(
                Arrays.asList( Arrays.asList( "Weird Meal", "7" ),
                    Arrays.asList( "Another Weird Meal", "8" ),
                    Arrays.asList( "Yet Another Weird Meal", "9" ) ) ) );

        // Removing 3 spurious orders.
        reservation.removeOrders( new ArrayList<List<String>>(
                Arrays.asList( Arrays.asList( "Weird Meal", "7" ),
                    Arrays.asList( "Another Weird Meal", "8" ),
                    Arrays.asList( "Yet Another Weird Meal", "9" ) ) ) );

        System.out.println( reservation.getEmployeeIdsOrderedFromRestaurant(
                "Sichuan Gourmet" ) );
        System.out.println( reservation.listOrders( ) );
        System.out.println( reservation.findNumberOfOrders( ) );
        System.out.println( reservation.findMostOrderedRestaurants( ) );
    }
}
