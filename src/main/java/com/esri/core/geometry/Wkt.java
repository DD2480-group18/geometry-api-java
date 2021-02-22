/*
 Copyright 1995-2015 Esri

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts Dept
 380 New York Street
 Redlands, California, USA 92373

 email: contracts@esri.com
 */

/**
 * document requirements:
 * This program extracts a value from a well known text(Wkt) by searching for keywords in the text
 * as I have not found any documentation on how the Wkt is supposed to look, we can only derive parts of it from the code
 * There is a keyword in the Wkt that will either be PROJCS or GEOGCS
 * After that, if the keyword is GEOGCS, there will be the keyword SPHEROID
 * Then there will be the keyword UNIT followed by keywords/values separated by commas and ended by an end bracket
 * after the end bracket the next value is what we are looking for
 */

package com.esri.core.geometry;

final class Wkt {
	public static boolean[] IDs = new boolean[33];
	public static double find_tolerance_from_wkt(String buffer) {
		double tolerance = -1.0;

		if (buffer != null && buffer.length() > 0) {
			IDs[0] = true;
			int n1, n2;

			n1 = buffer.indexOf("PROJCS");
			if (n1 >= 0) {
				IDs[1] = true;
				double factor = 0.0;

				n1 = buffer.lastIndexOf("UNIT");
				if (n1 >= 0) {
					IDs[2] = true;
					n1 = buffer.indexOf(',', n1 + 4);
					if (n1 > 0) {
						IDs[3] = true;
						n1++;
						n2 = buffer.indexOf(']', n1 + 1);
						if (n2 > 0) {
							IDs[4] = true;
							try {
								factor = Double.parseDouble(buffer.substring(
										n1, n2));
							} catch (NumberFormatException e) {
								IDs[5] = true;
								factor = 0.0;
							}
						} else IDs[6] = true;
					} else IDs[7] = true;
				} else IDs[8] = true;

				if (factor > 0.0) {
					IDs[9] = true;
					tolerance = (0.001 / factor);
				} else IDs[10] = true;
			}

			else {
				IDs[11] = true;
				n1 = buffer.indexOf("GEOGCS");
				if (n1 >= 0) {
					IDs[12] = true;
					double axis = 0.0;
					double factor = 0.0;

					n1 = buffer.indexOf("SPHEROID", n1 + 6);
					if (n1 > 0) {
						IDs[13] = true;
						n1 = buffer.indexOf(',', n1 + 8);
						if (n1 > 0) {
							IDs[14] = true;
							n1++;
							n2 = buffer.indexOf(',', n1 + 1);
							if (n2 > 0) {
								IDs[15] = true;
								try {
									axis = Double.parseDouble(buffer.substring(
											n1, n2));
								} catch (NumberFormatException e) {
									IDs[16] = true;
									axis = 0.0;
								}
							} else IDs[17] = true;

							if (axis > 0.0) {
								IDs[18] = true;
								n1 = buffer.indexOf("UNIT", n2 + 1);
								if (n1 >= 0) {
									IDs[19] = true;
									n1 = buffer.indexOf(',', n1 + 4);
									if (n1 > 0) {
										IDs[20] = true;
										n1++;
										n2 = buffer.indexOf(']', n1 + 1);
										if (n2 > 0) {
											IDs[21] = true;
											try {
												factor = Double
														.parseDouble(buffer
																.substring(n1,
																		n2));
											} catch (NumberFormatException e) {
												IDs[22] = true;
												factor = 0.0;
											}
										} else IDs[23] = true;
									} else IDs[24] = true;
								} else IDs[25] = true;
							} else IDs[26] = true;
						} else IDs[27] = true;
					} else IDs[28] = true;

					if (axis > 0.0 && factor > 0.0){
						IDs[29] = true;
						tolerance = (0.001 / (axis * factor));
					} else IDs[30] = true;
				}else IDs[31] = true;
			}
		} else IDs[32] = true;

		return tolerance;
	}
}
