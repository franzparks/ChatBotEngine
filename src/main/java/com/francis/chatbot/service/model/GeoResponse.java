/**
 * 
 */
package com.francis.chatbot.service.model;

import java.util.List;

/**
 * @author francisphiri
 *
 */
public class GeoResponse {
	List<Result> results;
    String status;

    static class Result {
        List<AddressComponent> address_components;
        String formatted_address;
        Geometry geometry;
        boolean partial_match;
        String place_id;
        List<String> types;

        static class AddressComponent {
            String long_name, short_name;
            List<String> types;
        }

        static class Geometry {
            Rect bounds;
            LatLng location;
            String location_type;
            Rect viewport;

            static class Rect {
                LatLng northeast, southwest;
            }

            static class LatLng {
                double lat, lng;
            }
        }
    }
}
