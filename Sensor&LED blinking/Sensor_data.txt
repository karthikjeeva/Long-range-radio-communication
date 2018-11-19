/*  
 *  LoRa 868 / 915MHz SX1272 Module
 *  
 *  Copyright (C) Libelium Comunicaciones Distribuidas S.L. 
 *  http://www.libelium.com 
 *  
 *  This program is free software: you can redistribute it and/or modify 
 *  it under the terms of the GNU General Public License as published by 
 *  the Free Software Foundation, either version 3 of the License, or 
 *  (at your option) any later version. 
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License 
 *  along with this program.  If not, see http://www.gnu.org/licenses/. 
 *  
 *  Version:           1.0
 *  Design:            David Gascón 
 *  Implementation:    Victor Boria & Luis Miguel Marti
 */
 
// Include the SX1272 and SPI library: 
#include "SX1272.h"

int e;
char message1 [60];
int ldr;
int ntc;
void get_temp();

void setup()
{
  // Print a start message
  printf("SX1272 module and Arduino: receive packets without ACK\n");
  
  // Power ON the module
  sx1272.ON();
  printf("Setting power ON: state ");
  printf("%d\n",e);
   
  // Set transmission mode and print the result
  e = sx1272.setMode(4);
  printf("Setting Mode: state ");
  printf("%d\n",e);
  
  // Select frequency channel
  e = sx1272.setChannel(CH_12_868);
  printf("Setting Channel: state ");
  printf("%d\n",e);
  
  // Select output power (Max, High or Low)
  e = sx1272.setPower('H');
  printf("Setting Power: state ");
  printf("%d\n",e);
  
  // Set the node address and print the result
  e = sx1272.setNodeAddress(2);
  printf("Setting node address: state ");
  printf("%d\n",e);
  
  // Print a success message
  printf("SX1272 successfully configured\n");
}

void loop(void)
{
    ldr = analogRead(1);
    get_temp();
    sprintf(message1,"LDR value: %i \r\nNTC value: %i\r\n",ldr,ntc);
    e = sx1272.sendPacketTimeout(3, message1);
    printf("Packet sent, state ");
    printf("%d\n",e);
    
    delay(5000);
}


void get_temp() {
  ntc = analogRead(0) * 5 / 1024.0;
  ntc = ntc - 0.5;
  ntc = ntc / 0.01;
}

int main (){
	setup();
	while(1){
		loop();
	}
	return (0);
}

        

        