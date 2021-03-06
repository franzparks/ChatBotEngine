/**
 * 
 */
package com.francis.chatbot.service.model.gmap;

import java.util.List;

/**
 * @author francisphiri
 *	Helper class to map json response from gmap api into a usable object
 */
public class GeoResponse {
	
	public List<Result> results;
    public String status;

    public static class Result {
    	public List<AddressComponent> address_components;
    	public String formatted_address;
    	public Geometry geometry;
    	public boolean partial_match;
    	public String place_id;
    	public List<String> types;

        static class AddressComponent {
            public String long_name, short_name;
            public List<String> types;
        }

        public static class Geometry {
        	public Rect bounds;
        	public LatLng location;
        	public String location_type;
        	public Rect viewport;

            public static class Rect {
            	public LatLng northeast, southwest;
            }

            public static class LatLng {
            	public double lat, lng;
            }
        }
    }
}
