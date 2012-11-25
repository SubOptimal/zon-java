/*
 Copyright 2012 Frank Dietrich

 This software distributed under the terms of the.
 GNU General Public License Version 3 (GPLv3) of the License,.
 or (at your option) any later version.

 This file is part of the library libevatr.

 libevatr is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 3 of the License, or
 (at your option) any later version.

 libevatr is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sub.optimal.zon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Example {

    /**
     * Get your API key on: http://developer.zeit.de/quickstart/
     */
    private final static String ZON_API_KEY = "INSERT_YOUR_ZEIT_API_KEY_HERE";

    /**
     * A basic code snippet to show how to access the Zeit Online API. see:
     * http://developer.zeit.de/quickstart/
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://api.zeit.de/content?q=subtitle:java&limit=42&fields=subtitle,uri");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent:", "https://github.com/SubOptimal/zon-java");
            conn.setRequestProperty("X-Authorization", ZON_API_KEY);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("ERROR: HTTP request error code: "
                        + conn.getResponseCode());
            }

            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            System.out.println("Response from Server...");
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
            isr.close();
        } catch (MalformedURLException e) {
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
