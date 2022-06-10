 #include "BluetoothSerial.h" 
BluetoothSerial ESP_BT; 
int incoming;
int BUT_1 = 4;  //кнопка 1
int BUT_2 = 5;  //кнопка 2
int BUT_3 = 16; //кнопка 3
int BUT_4 = 18; //кнопка 4


//                                                            НАЖАТИЯ КНОПОК
//=================================================================================================================================
void but1_on(){
  digitalWrite(BUT_1, HIGH);
  ESP_BT.println("кнопка 1 on");
}
void but1_off(){
  digitalWrite(BUT_1, LOW);
  ESP_BT.println("кнопка 1 off");
}

void but2_on(){
  digitalWrite(BUT_2, HIGH);
  ESP_BT.println("кнопка 2 on");
}
void but2_off(){
  digitalWrite(BUT_2, LOW);
  ESP_BT.println("кнопка 2 off");
}

void but3_on(){
  digitalWrite(BUT_3, HIGH);
  ESP_BT.println("кнопка 3 on");
}
void but3_off(){
  digitalWrite(BUT_3, LOW);
  ESP_BT.println("кнопка 3 off");
}


void but4_on(){
  digitalWrite(BUT_4, HIGH);
  ESP_BT.println("кнопка 4 on");     
}
void but4_off(){
  digitalWrite(BUT_4, LOW);
  ESP_BT.println("кнопка 4 off");     
}
//=================================================================================================================================

//=================================================================================================================================
void but1_long(){
  but1_on();
  delay(2100);
  but1_off();
}

void but2_long(){
  but2_on();
  delay(2100);
  but2_off();  
}


void but3_long(){
  but3_on();
  delay(2100);
  but3_off();
}

void but4_long(){
  but4_on();
  delay(2100);
  but4_off();

}



//==============================================================================================================================

void setup() {
  Serial.begin(9600);
  ESP_BT.begin("SYNC"); //Name of your Bluetooth Signal (имя нашего Bluetooth соединения)
  Serial.println("Bluetooth Device is Ready to Pair");
  pinMode (BUT_1, OUTPUT);//Specify that LED pin is output (режим работы контакта на вывод данных)
 
  pinMode (BUT_2, OUTPUT);//Specify that LED pin is output (режим работы контакта на вывод данных)
  pinMode (BUT_3, OUTPUT);//Specify that LED pin is output (режим работы контакта на вывод данных)
  pinMode (BUT_4, OUTPUT);//Specify that LED pin is output (режим работы контакта на вывод данных)
  if (ESP_BT.available()) //проверяем получаем ли мы что-нибудь по Bluetooth
  {
   
        but1_off(); 
        but2_off(); 
        but3_off(); 
        but4_off(); 
  }
}
void loop() {
  
  if (ESP_BT.available()) //проверяем получаем ли мы что-нибудь по Bluetooth
  {
    incoming = ESP_BT.read(); //считываем то, что мы принимаем 
    Serial.print("Received:"); Serial.println(incoming);

   switch(incoming){
      case 65://A  Нажатие кнопки 1 
        but1_on();
        delay(300);
        break;
         
      case 66://B   Нажатие кнопки 2
        but2_on();
        delay(300);
        break;
        
      case 67://C   Нажатие кнопки 3
        but3_on();
        delay(300);
         break;            
         
      case 68://D   Нажатие кнопки 4
        but4_on();
        delay(300);
        break;
      
      
      case 97://a   Удержание кнопки 1 
         but1_off();

         break;

      case 98://b Удержание кнопки 2
         but2_off();
         break;

      case 99://c  Удержание кнопки 3
         but3_off();
         break;

      case 100://d Удержание кнопки 4
         but4_off();
         break;

      case 48:
        but1_on();
        delay(200);
        but1_off();
        delay(200);
        but1_on();
        delay(200);
        but1_off();
        
      case 49:
        but2_on();
        delay(200);
        but2_off();
        delay(200);
        but2_on();
        delay(200);
        but2_off();
        
      case 50:
        but3_on();
        delay(200);
        but3_off();
        delay(200);
        but3_on();
        delay(200);
        but3_off();

      case 51:
        but4_on();
        delay(200);
        but4_off();
        delay(200);
        but4_on();
        delay(200);
        but4_off();     
   }
  }
  delay(100);
}
