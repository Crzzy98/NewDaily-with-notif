package com.example.newdaily;

public class Day {
	
	private int h1, h2 , h3, h4, m1, m2 , m3 ,m4;
	
	public Day(int time, int taskAmount) {
		switch(taskAmount){
			case 4:
				fourTasks(time);
				break;
			case 3:
				threeTasks(time);
				break;
			case 2:
				twoTasks(time);
				break;
			case 1:
				oneTask(time);
				break;
		}
		
	}

	private void oneTask(int time) {
		this.h1 = time;//hours
	}

	private void twoTasks(int time) {
		if((time*35)/100 >= 1) {//First`

			this.h1 = (int)(time*50)/100;//hours

			this.m1 = (int)((((time*50)%100))*60 )/100;//minutes

		}else {

			this.m1 = (int)((((time*50)%100)*60))/100;

		}

		if((time*25)/100 >= 1) {//2nd

			this.h2 = (int)(time*50)/100;

			this.m2 = (int)((((time*50)%100))*60 )/100;

		}else {

			this.m2 = (int)((((time*50)%100))*60 )/100;

		}
	}

	private void threeTasks(int time) {
		if((time*35)/100 >= 1) {//First`

			this.h1 = (int)(time*45)/100;//hours

			this.m1 = (int)((((time*45)%100))*60 )/100;//minutes

		}else {

			this.m1 = (int)((((time*45)%100)*60))/100;

		}

		if((time*25)/100 >= 1) {//2nd

			this.h2 = (int)(time*35)/100;

			this.m2 = (int)((((time*35)%100))*60 )/100;

		}else {

			this.m2 = (int)((((time*35)%100))*60 )/100;

		}

		if((time*20)/100 >= 1) {//3rd

			this.h3 = (int)(time*20)/100;

			this.m3 = (int)((((time*20)%100))*60 )/100;

		}else {

			this.m3 = (int)((((time*20)%100))*60 )/100;

		}
	}

	private void fourTasks(int time) {
		if((time*35)/100 >= 1) {//First`

			this.h1 = (int)(time*35)/100;//hours

			this.m1 = (int)((((time*35)%100))*60 )/100;//minutes

		}else {

			this.m1 = (int)((((time*35)%100)*60))/100;

		}

		if((time*25)/100 >= 1) {//2nd

			this.h2 = (int)(time*25)/100;

			this.m2 = (int)((((time*25)%100))*60 )/100;

		}else {

			this.m2 = (int)((((time*25)%100))*60 )/100;

		}

		if((time*20)/100 >= 1) {//3rd

			this.h3 = (int)(time*20)/100;

			this.m3 = (int)((((time*20)%100))*60 )/100;

		}else {

			this.m3 = (int)((((time*20)%100))*60 )/100;

		}
		if((time*15)/100 >= 1) {//4th

			this.h4 = (int)(time*15)/100;

			this.m4 = (int)((((time*15)%100))*60 )/100;

		}else {

			this.m4 = (int)((((time*15)%100))*60 )/100;

		}

	}

	//METHOD TO GET TIMES
	public int getT(int num) {
		
		int temp = 0;
		
		switch(num) {
		case(1):
			temp = this.h1;
		break;
		case(2):
			temp = this.h2;
		break;
		case(3):
			temp = this.h3;
		break;
		case(4):
			temp = this.h4;
		break;
		case(11):
			temp = this.m1;
		break;
		case(22):
			temp = this.m2;
		break;
		case(33):
			temp = this.m3;
		break;
		case(44):
			temp = this.m4;
		break;
		}
		
		return temp;
		

	}



}