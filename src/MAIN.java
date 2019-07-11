package virtcompALL;
import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.lang.reflect.Method; 
import java.lang.reflect.Field; 
import java.lang.reflect.Constructor; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream;
import java.io.IOException; 
import java.util.Scanner;
import java.util.Scanner;
public class MAIN extends PApplet {
//Main Variable Declaration:
AND[] allAnds=new AND[0];
NOT[] allNots=new NOT[0];
OR[] allOrs=new OR[0];
NOR[] allNors=new NOR[0];
NAND[] allNands=new NAND[0];
XOR[] allXors=new XOR[0];
mem[] allmems=new mem[0];

INPUT[] allIns=new INPUT[0];
LED[] allLeds=new LED[0];
int andC=0;
int notC=0;
int orC=0;
int norC=0;
int nandC=0;
int xorC=0;
int inputC=0;
int ledC=0;
int memC=0;
int textY=10;
int t=0;
int ALUwid=500;
int ALUhei=250;
int eip=0;
String input="";
String doneinput="";
init a;
public String consoleOutput="Output:   ";
public int[][] memMatrix={
		{0,0,0,0},//operation setup
		{0,0,0,0},//operation setup
		{0,0,0,0},//input/output
		{0,0,0,0},//if
		{0,0,0,0},//jump to
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},//number output
		{0,0,0,0},//temporary moving
};
public int[][] memMatrix2={
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
};
public int[][] instMatrix={
		{1,1,1,0,0,0,0},
		
		{1,0,1,0,0,0,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		
		{1,0,1,0,0,0,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		
		{0,0,0,0,0,0,0},
		
		{0,0,0,1,1,1,1},
		{0,0,0,1,1,1,1},
		{0,0,0,1,1,1,1},
		
		{0,1,0,0,0,1,0},
		{0,1,1,1,1,1,0},
		{1,1,0,0,1,0,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		{1,1,0,0,1,0,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		{1,0,0,0,0,0,0},
		{1,1,1,0,0,0,0},
		
		{1,1,0,0,1,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		{1,1,0,1,1,1,1},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		{1,0,0,0,0,0,0},
		{1,1,1,0,0,0,0},
		
		{1,1,0,0,1,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		{1,1,0,1,1,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		{1,0,0,0,0,0,0},
		{1,1,1,0,0,0,0},
		
		{1,1,0,0,1,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		{1,1,0,0,1,0,1},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		{1,0,0,0,0,0,0},
		{1,1,1,0,0,0,0},
		
		{1,1,0,0,0,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,0},
		{1,1,0,0,0,0,1},
		{0,1,0,0,0,1,0},
		{0,1,1,0,0,0,1},
		{1,0,0,0,0,0,0}
};

//---------------Inputs, Outputs:---------------//
INPUT a0;
INPUT a1;
INPUT a2;
INPUT a3;
INPUT b0;
INPUT b1;
INPUT b2;

INPUT b3;
INPUT s0;
INPUT s1;
INPUT s2;
INPUT s3;
INPUT c;
INPUT m;

LED f0;
LED f1;
LED f2;
LED f3;
LED c4;
LED x;
LED y;
public void setup() {
  stroke(0);
  frameRate(2);
  println("Initializing OpenGL...");
  textSize(10);
  println("Initializing Logic...");
  a=new init();
  println("Logic Initialization Successful, Initializing Param Classes...");
  s0=new INPUT(0,0,"S0");
  s1=new INPUT(20,0,"S1");
  s2=new INPUT(40,0,"S2");
  s3=new INPUT(60,0,"S3");
  a0=new INPUT(80,0,"A0");
  a1=new INPUT(100,0,"A1");
  a2=new INPUT(120,0,"A2");
  a3=new INPUT(140,0,"A3");
  b0=new INPUT(160,0,"B0");
  b1=new INPUT(180,0,"B1");
  b2=new INPUT(200,0,"B2");
  b3=new INPUT(220,0,"B3");
  c=new INPUT(240,0,"C");
  m=new INPUT(260,0,"M");
  println("Param Input Class Initialization Successful");
  f0=new LED(10,ALUhei-10,"F0");
  f1=new LED(30,ALUhei-10,"F1");
  f2=new LED(50,ALUhei-10,"F2");
  f3=new LED(70,ALUhei-10,"F3");
  c4=new LED(90,ALUhei-10,"C4");
  x=new LED(110,ALUhei-10,"X");
  y=new LED(130,ALUhei-10,"Y");
  println("Param Output Class Initialization Successful");
  println("COMPLETE Logic Initialized");
  println("Successfully Initialized "+str(andC+notC+orC+norC+nandC+xorC)+" Logic Gates");
}
public void drawsub() {
	background(220);
	if(instMatrix[eip].length==8) {
		if(eip<instMatrix.length) {
			opex(conv(instMatrix[eip][0]),conv(instMatrix[eip][1]),conv(instMatrix[eip][2]),conv(instMatrix[eip][3]),conv(instMatrix[eip][4]),conv(instMatrix[eip][5]),conv(instMatrix[eip][6]),conv(instMatrix[eip][7]));
		}
	} else if(instMatrix[eip].length==7) {
		if(eip<instMatrix.length) {
			opexO(conv(instMatrix[eip][0]),conv(instMatrix[eip][1]),conv(instMatrix[eip][2]),conv(instMatrix[eip][3]),conv(instMatrix[eip][4]),conv(instMatrix[eip][5]),conv(instMatrix[eip][6]));
		}
	}
	if(eip<instMatrix.length-1) {
		eip++;
	}	
	background(220);
	UPDATE_CONNECTIONS_1();
	UPDATE_CONNECTIONS_2();
	int opc=multiplexer(ALUwid/5,ALUhei-40,"OPCODE",s3.output,s2.output,s1.output,s0.output,0);
	int ina=multiplexer(2*(ALUwid/5),ALUhei-40,"INPUT A",a3.output,a2.output,a1.output,a0.output,0);
	int inb=multiplexer(3*(ALUwid/5),ALUhei-40,"INPUT B",b3.output,b2.output,b1.output,b0.output,0);
	multiplexer(4*(ALUwid/5),ALUhei-40,"OUTPUT",f3.input,f2.input,f1.input,f0.input,0);
	multiplexer(width/2, height/2,"NUM OUTPUT",conv(memMatrix[14][3]),conv(memMatrix[14][2]),conv(memMatrix[14][1]),conv(memMatrix[14][0]),0);
	text(consoleOutput,0,height-30);
	text(doneinput,0,height-70);
	dispMem(width/2,20);
	stroke(0);
	line(0,ALUhei,ALUwid,ALUhei);
	line(ALUwid,0,ALUwid,ALUhei);
	noStroke();
	
	t++;
}
public void upda() {
	background(220);
	UPDATE_CONNECTIONS_1();
	UPDATE_CONNECTIONS_2();
	int opc=multiplexer(ALUwid/5,ALUhei-40,"OPCODE",s3.output,s2.output,s1.output,s0.output,0);
	int ina=multiplexer(2*(ALUwid/5),ALUhei-40,"INPUT A",a3.output,a2.output,a1.output,a0.output,0);
	int inb=multiplexer(3*(ALUwid/5),ALUhei-40,"INPUT B",b3.output,b2.output,b1.output,b0.output,0);
	multiplexer(4*(ALUwid/5),ALUhei-40,"OUTPUT",f3.input,f2.input,f1.input,f0.input,0);
	multiplexer(width/2, height/2,"NUM OUTPUT",conv(memMatrix[14][3]),conv(memMatrix[14][2]),conv(memMatrix[14][1]),conv(memMatrix[14][0]),0);
	text(consoleOutput,0,height-30);
	text(doneinput,0,height-70);
	dispMem(width/2,20);
	stroke(0);
	line(0,ALUhei,ALUwid,ALUhei);
	line(ALUwid,0,ALUwid,ALUhei);
	noStroke();
}
public void draw() {

}
public void keyTyped() {
	if(key=='1'||key=='0') {
		input+=key;
	} else if(key==' '||key=='\n') {
		drawsub();
		input="";
	} else {
	}
}
public void mouseClicked() {
	UPDATE_INPUTS();
}
public void dispMem(int x, int y) {
	for(int[] o : memMatrix) {
		text(convintlist(o),x,y);
		y+=10;
	}
}
public String convintlist(int[] a) {
	String ret="{";
	for(int i=a.length-1;i>=0;i--) {
		int p=a[i];
		ret=ret+str(p)+",";
	}
	ret=ret+"}";
	return ret;
}
public void opex(boolean in1, boolean in2, boolean in3, boolean in4, boolean p1, boolean p2, boolean p3, boolean p4) {
	//Multiplex:
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int mul=Integer.parseInt(intstr,2);
	//Execute:
	if(mul==0) {
		op0(p1,p2,p3,p4);
	} else if(mul==1) {
		op1();
	} else if(mul==2) {
		op2(p1,p2,p3,p4);
	} else if(mul==3) {
		op3(p1,p2,p3,p4);
	} else if(mul==4) {
		op4(p1,p2,p3,p4);
	} else if(mul==5) {
		op5(p1,p2,p3,p4);
	} else if(mul==6) {
		op6(p1,p2,p3,p4);
	} else if(mul==7) {
		op7();
	} else if(mul==8) {
		op8(p1,p2,p3,p4);
	} else {
		println("OpCase error: Not chosen. Got: "+str(mul));
	}
}
public void opexO(boolean in1, boolean in2, boolean in3, boolean p1, boolean p2, boolean p3, boolean p4) {
	//Multiplex:
	String intstr=conv(in1)+conv(in2)+conv(in3);
	int mul=Integer.parseInt(intstr,2);
	//Execute:
	if(mul==0) {
		op0(p1,p2,p3,p4);
	} else if(mul==1) {
		op1();
	} else if(mul==2) {
		op2(p1,p2,p3,p4);
	} else if(mul==3) {
		op3(p1,p2,p3,p4);
	} else if(mul==4) {
		op4(p1,p2,p3,p4);
	} else if(mul==5) {
		op5(p1,p2,p3,p4);
	} else if(mul==6) {
		op6(p1,p2,p3,p4);
	} else if(mul==7) {
		op7();
	} else {
		println("OpCase error: Not chosen. Got: "+str(mul));
	}
}
public int getStuffX(int i) {
  int x=i%(floor(ALUwid/30))*30;
  return x;
}
public int getStuffY(int i) {
  int y=floor(i/(ALUwid/30))*20+10;
  return y;
}
public int multiplexer(int x, int y,String title, boolean in1, boolean in2, boolean in3, boolean in4,int plus) {
	String in=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int out=Integer.parseInt(in,2);
	fill(0);
	text(title,x,y);
	text(out,x,y+10);
	return out;
}
public void op0(boolean in1,boolean in2,boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	if(p>=10) {
		s0.output=true;
		s1.output=false;
		s2.output=false;
		s3.output=true;
		c.output=true;
		
		a0.output=conv(memMatrix[0][0]);
		a1.output=conv(memMatrix[0][1]);
		a2.output=conv(memMatrix[0][2]);
		a3.output=conv(memMatrix[0][3]);
		
		b0.output=conv(memMatrix[1][0]);
		b1.output=conv(memMatrix[1][1]);
		b2.output=conv(memMatrix[1][2]);
		b3.output=conv(memMatrix[1][3]);
	
		upda();
	}
	memMatrix[2][0]=Integer.parseInt(conv(f0.input));
	memMatrix[2][1]=Integer.parseInt(conv(f1.input));
	memMatrix[2][2]=Integer.parseInt(conv(f2.input));
	memMatrix[2][3]=Integer.parseInt(conv(f3.input));
}

public void op1() {
	s0.output=false;
	s1.output=true;
	s2.output=true;
	s3.output=false;
	c.output=false;
	
	a0.output=conv(memMatrix[0][0]);
	a1.output=conv(memMatrix[0][1]);
	a2.output=conv(memMatrix[0][2]);
	a3.output=conv(memMatrix[0][3]);
	
	b0.output=conv(memMatrix[1][0]);
	b1.output=conv(memMatrix[1][1]);
	b2.output=conv(memMatrix[1][2]);
	b3.output=conv(memMatrix[1][3]);

	drawsub();
	
	memMatrix[2][0]=Integer.parseInt(conv(f0.input));
	memMatrix[2][1]=Integer.parseInt(conv(f1.input));
	memMatrix[2][2]=Integer.parseInt(conv(f2.input));
	memMatrix[2][3]=Integer.parseInt(conv(f3.input));
}

public void op2(boolean in1,boolean in2,boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	memMatrix[15]=memMatrix[p];
	int[] t={0,0,0,0};
	memMatrix[p]=t;
}

public void op3(boolean in1,boolean in2,boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	memMatrix[p]=memMatrix[15];
	int[] t={0,0,0,0};
	memMatrix[15]=t;
}

public void op4(boolean in1,boolean in2,boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	int asc=Integer.parseInt(str(memMatrix[p][3])+str(memMatrix[p][2])+str(memMatrix[p][1])+str(memMatrix[p][0])+str(memMatrix[p+1][3])+str(memMatrix[p+1][2])+str(memMatrix[p+1][1])+str(memMatrix[p+1][0]),2);
	String out1=new String(new char[]{(char)asc});
	consoleOutput=consoleOutput+out1;
}
public void op5(boolean in1, boolean in2, boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	if(p<10) {
		memMatrix[2][0]=Integer.parseInt(str(input.charAt(3)));
		memMatrix[2][1]=Integer.parseInt(str(input.charAt(2)));
		memMatrix[2][2]=Integer.parseInt(str(input.charAt(1)));
		memMatrix[2][3]=Integer.parseInt(str(input.charAt(0)));
	} else {
		//DO NOTHING
	}
}
public void op6(boolean in1,boolean in2,boolean in3, boolean in4) {
	memMatrix[2][0]=convi(in4);
	memMatrix[2][1]=convi(in3);
	memMatrix[2][2]=convi(in2);
	memMatrix[2][3]=convi(in1);
}
public void op7() {
	int[][] blank={
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
		{0,0,0,0},
	};
	memMatrix=blank;
}
public void op8(boolean in1, boolean in2, boolean in3, boolean in4) {
	String intstr=conv(in1)+conv(in2)+conv(in3)+conv(in4);
	int p=Integer.parseInt(intstr,2);
	String intstr1=str(memMatrix[p][3])+str(memMatrix[p][2])+str(memMatrix[p][1])+str(memMatrix[p][0]);
	String intstr2=str(memMatrix[3][3])+str(memMatrix[3][2])+str(memMatrix[3][1])+str(memMatrix[3][0]);
	String intstr3=str(memMatrix[4][3])+str(memMatrix[4][2])+str(memMatrix[4][1])+str(memMatrix[4][0]);
	if(intstr1==intstr2) {
		eip=0;//Integer.parseInt(intstr3,2);
	}
}
String conv(boolean in) {
	String out;
	if(in) {
		out="1";
	} else {
		out="0";
	}
	return out;
}
boolean conv(int in) {
	if(in==0) {
		return false;
	} else {
		return true;
	}
}
int convi(boolean in) {
	int out;
	if(in) {
		out=1;
	} else {
		out=0;
	}
	return out;
}
class AND {
  boolean input1=false;
  boolean input2=false;
  boolean output=false;
  String name;
  public void update() {
    if (this.input1&&this.input2) {
      this.output=true;
    } else {
      this.output=false;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  AND(String nm) {
	  name=nm;
	  allAnds=Aand(allAnds, this);
	  andC++;
  }
}

class NAND {
  boolean input1=false;
  boolean input2=false;
  boolean output=true;
  String name;
  public void update() {
    if (this.input1&&this.input2) {
      this.output=false;
    } else {
      this.output=true;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  NAND(String nm) {
	name=nm;
    allNands=Anand(allNands, this);
    nandC++;
  }
}

class INPUT {
  boolean output=false;
  float x;
  float y;
  String txt;
  public void update() {
    if (output) {
      fill(255, 255, 0);
    } else {
      fill(0, 0, 0);
    }
    rect(x, y, 20, 10);
    if (output) {
      fill(0);
    } else {
      fill(255);
    }
    text(txt, x, y+10);
  }
  public void CliUpdate() {
    if (mouseX<=x+20&&mouseX>=x&&mouseY<=y+10&&mouseY>=y) {
      //output=!output;
    }
  }
  INPUT(float xt, float yt, String txtt) {
    x=xt;
    y=yt;
    txt=txtt;
    allIns=Ain(allIns, this);
    inputC++;
    if (output) {
      fill(255, 255, 0);
    } else {
      fill(0, 0, 0);
    }
    rect(x, y, 20, 10);
    if (output) {
      fill(0);
    } else {
      fill(255);
    }
    text(txt, x, y+10);
  }
}

class LED {
  boolean input=false;
  float x;
  float y;
  String txt;
  public void update() {
    if (input) {
      fill(255, 255, 0);
    } else {
      fill(0, 0, 0);
    }
    ellipse(x, y, 20, 20);
    if (input) {
      fill(0);
    } else {
      fill(255);
    }
    text(txt, x-10, y+5);
  }
  LED(float xt, float yt, String txtt) {
    x=xt;
    y=yt;
    txt=txtt;
    allLeds=Aled(allLeds, this);
    ledC++;
    noStroke();
    if (input) {
      fill(255, 255, 0);
    } else {
      fill(0, 0, 0);
    }
    ellipse(x, y, 20, 20);
    if (input) {
      fill(0);
    } else {
      fill(255);
    }
    text(txt, x-10, y+5);
  }
}

class NOT {
  boolean input=false;
  boolean output=true;
  String name;
  public void update() {
    if (!input) {
      output=true;
    } else {
      output=false;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  NOT(String nm) {
	  name=nm;
    allNots=Anot(allNots, this);
    notC++;
  }
}

class OR {
  boolean input1=false;
  boolean input2=false;
  boolean output=false;
  String name;
  public void update() {
    if (this.input1||this.input2) {
      this.output=true;
    } else {
      this.output=false;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  OR(String nm) {
	name=nm;
    allOrs=Aor(allOrs, this);
    orC++;
  }
}

class NOR {
  boolean input1=false;
  boolean input2=false;
  boolean output=true;
  String name;
  public void update() {
    if (this.input1||this.input2) {
      this.output=false;
    } else {
      this.output=true;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  NOR(String nm) {
	name=nm;
    allNors=Anor(allNors, this);
    norC++;
  }
}

class XOR {
  boolean input1=false;
  boolean input2=false;
  boolean output=false;
  String name;
  public void update() {
    if (this.input1&&!this.input2) {
      this.output=true;
    } else if (!this.input1&&this.input2) {
      this.output=true;
    } else {
      this.output=false;
    }
  }
  public void display(int x, int y) {
    y+=20;
    if (this.output) {
      fill(255, 255, 0);
    } else {
      fill(255);
    }
    rect(x, y, 30, 20);
    fill(0);
    text(this.name.substring(0, 2), x, y+10);
    text(this.name.substring(2, this.name.length()), x, y+20);
  }
  XOR(String nm) {
	name=nm;
    allXors=Axor(allXors, this);
    xorC++;
  }
}

public class mem {
	boolean r=false;
	boolean s=false;
	NOR N1=new NOR("memSN1");
	NOR N2=new NOR("memSN2");
	void update() {
		N1.input1=r;
		N1.input2=N2.output;
		N2.input2=s;
		N2.input1=N1.output;
		N1.update();
		N2.update();
	}
	mem() {
		allmems=Amem(allmems,this);
		memC++;
	}
}

public AND[] Aand(AND[] a, AND b) {
  AND[] done=new AND[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public NOT[] Anot(NOT[] a, NOT b) {
  NOT[] done=new NOT[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public OR[] Aor(OR[] a, OR b) {
  OR[] done=new OR[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public NAND[] Anand(NAND[] a, NAND b) {
  NAND[] done=new NAND[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public NOR[] Anor(NOR[] a, NOR b) {
  NOR[] done=new NOR[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public XOR[] Axor(XOR[] a, XOR b) {
  XOR[] done=new XOR[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public mem[] Amem(mem[] a, mem b) {
  mem[] done=new mem[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public LED[] Aled(LED[] a, LED b) {
  LED[] done=new LED[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public INPUT[] Ain(INPUT[] a, INPUT b) {
  INPUT[] done=new INPUT[a.length+1];
  for (int i=0; i<a.length; i++) {
    done[i]=a[i];
  }
  done[a.length]=b;
  return done;
}
public void display() {
  for(int i=0;i<andC;i++) {
    int s=i;
    int x=getStuffX(s);
    int y=getStuffY(s);
    allAnds[i].display(x,y);
  }
  for(int i=0;i<nandC;i++) {
    int s=i+andC;
    int x=getStuffX(s);
    int y=getStuffY(s);
    allNands[i].display(x,y);
  }
  for(int i=0;i<orC;i++) {
    int s=i+andC+nandC;
    int x=getStuffX(s);
    int y=getStuffY(s);
    allOrs[i].display(x,y);
  }
  for(int i=0;i<norC;i++) {
    int s=i+andC+nandC+orC;
    int x=getStuffX(s);
    int y=getStuffY(s);
    if(allNors[i].name!="memSN1"&&allNors[i].name!="memSN2") {
    	allNors[i].display(x,y);
    } else {
    	allNors[i].display(x+ALUwid,y);
    }
  }
  for(int i=0;i<notC;i++) {
    int s=i+andC+nandC+orC+norC;
    int x=getStuffX(s);
    int y=getStuffY(s);
    allNots[i].display(x,y);
  }
  for(int i=0;i<xorC;i++) {
    int s=i+andC+nandC+orC+norC+notC;
    int x=getStuffX(s);
    int y=getStuffY(s);
    allXors[i].display(x,y);
  }
}
class init {
//---------------MEM:-----------------//

//---------------Blk 1:---------------//

  //NOT:

  public NOT B1NT1=new NOT("B1NT1");
  //AND:

  public AND B1AN1=new AND("B1AN1");
  public AND B1AN2=new AND("B1AN2");
  public AND B1AN3=new AND("B1AN3");
  public AND B1AN4=new AND("B1AN4");
  public AND B1AN5=new AND("B1AN5");
  public AND B1AN6=new AND("B1AN6");
  public AND B1AN7=new AND("B1AN7");
  //OR:

  public OR B1OR1=new OR("B1OR1");
  //NOR:

  public NOR B1NR1=new NOR("B1NR1");
  public NOR B1NR2=new NOR("B1NR2");
  //---------------Blk 2:---------------//

  //NOT:

  public NOT B2NT1=new NOT("B2NT1");
  //AND:

  public AND B2AN1=new AND("B2AN1");
  public AND B2AN2=new AND("B2AN2");
  public AND B2AN3=new AND("B2AN3");
  public AND B2AN4=new AND("B2AN4");
  public AND B2AN5=new AND("B2AN5");
  public AND B2AN6=new AND("B2AN6");
  public AND B2AN7=new AND("B2AN7");
  //OR:

  public OR B2OR1=new OR("B2OR1");
  //NOR:

  public NOR B2NR1=new NOR("B2NR1");
  public NOR B2NR2=new NOR("B2NR2");
  //---------------Blk 3:---------------//

  //NOT:

  public NOT B3NT1=new NOT("B3NT1");
  //AND:

  public AND B3AN1=new AND("B3AN1");
  public AND B3AN2=new AND("B3AN2");
  public AND B3AN3=new AND("B3AN3");
  public AND B3AN4=new AND("B3AN4");
  public AND B3AN5=new AND("B3AN5");
  public AND B3AN6=new AND("B3AN6");
  public AND B3AN7=new AND("B3AN7");
  //OR:

  public OR B3OR1=new OR("B3OR1");
  //NOR:

  public NOR B3NR1=new NOR("B3NR1");
  public NOR B3NR2=new NOR("B3NR2");
  //---------------Blk 4:---------------//

  //NOT:

  public NOT B4NT1=new NOT("B4NT1");
  public NOT B4NT2=new NOT("B4NT2");
  //AND:

  public AND B4AN1=new AND("B4AN1");
  public AND B4AN2=new AND("B4AN2");
  public AND B4AN3=new AND("B4AN3");
  public AND B4AN4=new AND("B4AN4");
  public AND B4AN5=new AND("B4AN5");
  public AND B4AN6=new AND("B4AN6");
  public AND B4AN7=new AND("B4AN7");
  //OR:

  public OR B4OR1=new OR("B4OR1");
  //NOR:

  public NOR B4NR1=new NOR("B4NR1");
  public NOR B4NR2=new NOR("B4NR2");
  //---------------Blk 5:---------------//

  //NOT:

  public NOT B5NT1=new NOT("B5NT1");
  public NOT B5NT2=new NOT("B5NT2");
  //AND:

  public AND B5AN1=new AND("B5AN1");
  public AND B5AN2=new AND("B5AN2");
  public AND B5AN3=new AND("B5AN3");
  public AND B5AN4=new AND("B5AN4");
  public AND B5AN5=new AND("B5AN5");
  public AND B5AN6=new AND("B5AN6");
  public AND B5AN7=new AND("B5AN7");
  public AND B5AN8=new AND("B5AN8");
  public AND B5AN9=new AND("B5AN9");
  public AND B5AN10=new AND("B5AN10");
  public AND B5AN11=new AND("B5AN11");
  public AND B5AN12=new AND("B5AN12");
  public AND B5AN13=new AND("B5AN13");
  public AND B5AN14=new AND("B5AN14");
  //OR:

  public OR B5OR1=new OR("B5OR1");
  public OR B5OR2=new OR("B5OR2");
  public OR B5OR3=new OR("B5OR3");
  //NOR:

  public NOR B5NR1=new NOR("B5NR1");
  //---------------Blk 6:---------------//

  //NOT:

  public NOT B6NT1=new NOT("B6NT1");
  public NOT B6NT2=new NOT("B6NT2");
  //AND:

  public AND B6AN1=new AND("B6AN1");
  public AND B6AN2=new AND("B6AN2");
  public AND B6AN3=new AND("B6AN3");
  public AND B6AN4=new AND("B6AN4");
  public AND B6AN5=new AND("B6AN5");
  public AND B6AN6=new AND("B6AN6");
  public AND B6AN7=new AND("B6AN7");
  public AND B6AN8=new AND("B6AN8");
  public AND B6AN9=new AND("B6AN9");
  public AND B6AN10=new AND("B6AN10");
  //OR:

  public OR B6OR1=new OR("B6OR1");
  public OR B6OR2=new OR("B6OR2");
  //NOR:

  public NOR B6NR1=new NOR("B6NR1");
  //XOR:

  public XOR B6XR1=new XOR("B6XR1");
  public XOR B6XR2=new XOR("B6XR2");
  //---------------Blk 7:---------------//

  //AND:

  public AND B7AN1=new AND("B7AN1");
  public AND B7AN2=new AND("B7AN2");
  public AND B7AN3=new AND("B7AN3");
  public AND B7AN4=new AND("B7AN4");
  public AND B7AN5=new AND("B7AN5");
  public AND B7AN6=new AND("B7AN6");
  public AND B7AN7=new AND("B7AN7");
  public AND B7AN8=new AND("B7AN8");
  public AND B7AN9=new AND("B7AN9");
  //OR:

  public OR B7OR1=new OR("B7OR1");
  //NOR:

  public NOR B7NR1=new NOR("B7NR1");
  //XOR:

  public XOR B7XR1=new XOR("B7XR1");
  public XOR B7XR2=new XOR("B7XR2");
  //---------------Blk 8:---------------//

  //NOT:

  public NOT B8NT1=new NOT("B8NT1");
  //AND:

  public AND B8AN1=new AND("B8AN1");
  public AND B8AN2=new AND("B8AN2");
  public AND B8AN3=new AND("B8AN3");
  //NAND:

  public NAND B8ND1=new NAND("B8ND1");
  //NOR:

  public NOR B8NR1=new NOR("B8NR1");
  //XOR:

  public XOR B8XR1=new XOR("B8XR1");
  public XOR B8XR2=new XOR("B8XR2");
  public XOR B8XR3=new XOR("B8XR3");
  public XOR B8XR4=new XOR("B8XR4");

  init() {}
}
public void UPDATE() {
  display();
  for(int i=0;i<inputC;i++) {
    allIns[i].update();
  }
  for(int i=0;i<andC;i++) {
    allAnds[i].update();
  }
  for(int i=0;i<nandC;i++) {
    allNands[i].update();
  }
  for(int i=0;i<orC;i++) {
    allOrs[i].update();
  }
  for(int i=0;i<norC;i++) {
	  if(allNors[i].name!="memSN1"&&allNors[i].name!="memSN2") {
			  allNors[i].update();
	  }
  }
  for(int i=0;i<notC;i++) {
    allNots[i].update();
  }
  for(int i=0;i<xorC;i++) {
    allXors[i].update();
  }
  for(int i=0;i<ledC;i++) {
    allLeds[i].update();
  }
  for(int i=0;i<memC;i++) {
	  allmems[i].update();
  }
}
public void UPDATE_CONNECTIONS_1() {
  //phase 0:
  a.B1NT1.input=b3.output;
  a.B2NT1.input=b2.output;
  a.B3NT1.input=b1.output;
  a.B4NT1.input=b0.output;
  a.B8NT1.input=m.output;
  UPDATE();
  //phase 1:
  a.B1AN1.input1=b3.output;
  a.B1AN1.input2=s3.output;
  a.B1AN3.input1=a3.output;
  a.B1AN3.input2=s2.output;
  a.B1AN5.input1=a.B1NT1.output;
  a.B1AN5.input2=s1.output;
  a.B1AN6.input1=s0.output;
  a.B1AN6.input2=b3.output;
  a.B1AN7.input1=a3.output;
  a.B1AN7.input2=a3.output;
  
  a.B2AN1.input1=b2.output;
  a.B2AN1.input2=s3.output;
  a.B2AN3.input1=a2.output;
  a.B2AN3.input2=s2.output;
  a.B2AN5.input1=a.B2NT1.output;
  a.B2AN5.input2=s1.output;
  a.B2AN6.input1=s0.output;
  a.B2AN6.input2=b2.output;
  a.B2AN7.input1=a2.output;
  a.B2AN7.input2=a2.output;
  
  a.B3AN1.input1=b1.output;
  a.B3AN1.input2=s3.output;
  a.B3AN3.input1=a1.output;
  a.B3AN3.input2=s2.output;
  a.B3AN5.input1=a.B3NT1.output;
  a.B3AN5.input2=s1.output;
  a.B3AN6.input1=s0.output;
  a.B3AN6.input2=b1.output;
  a.B3AN7.input1=a1.output;
  a.B3AN7.input2=a1.output;
  
  a.B4AN1.input1=b0.output;
  a.B4AN1.input2=s3.output;
  a.B4AN3.input1=a0.output;
  a.B4AN3.input2=s2.output;
  a.B4AN5.input1=a.B4NT1.output;
  a.B4AN5.input2=s1.output;
  a.B4AN6.input1=s0.output;
  a.B4AN6.input2=b0.output;
  a.B4AN7.input1=a0.output;
  a.B4AN7.input2=a0.output;
  UPDATE();
  //phase 2:
  a.B1AN2.input1=a.B1AN1.output;
  a.B1AN2.input2=a3.output;
  a.B1AN4.input1=a.B1AN3.output;
  a.B1AN4.input2=a.B1NT1.output;
  
  a.B2AN2.input1=a.B2AN1.output;
  a.B2AN2.input2=a2.output;
  a.B2AN4.input1=a.B2AN3.output;
  a.B2AN4.input2=a.B2NT1.output;
  
  a.B3AN2.input1=a.B3AN1.output;
  a.B3AN2.input2=a1.output;
  a.B3AN4.input1=a.B3AN3.output;
  a.B3AN4.input2=a.B3NT1.output;
  
  a.B4AN2.input1=a.B4AN1.output;
  a.B4AN2.input2=a0.output;
  a.B4AN4.input1=a.B4AN3.output;
  a.B4AN4.input2=a.B4NT1.output;
  UPDATE();
  //phase 3:
  a.B1NR1.input1=a.B1AN2.output;
  a.B1NR1.input2=a.B1AN4.output;
  a.B1OR1.input1=a.B1AN5.output;
  a.B1OR1.input2=a.B1AN6.output;
  
  a.B2NR1.input1=a.B2AN2.output;
  a.B2NR1.input2=a.B2AN4.output;
  a.B2OR1.input1=a.B2AN5.output;
  a.B2OR1.input2=a.B2AN6.output;
  
  a.B3NR1.input1=a.B3AN2.output;
  a.B3NR1.input2=a.B3AN4.output;
  a.B3OR1.input1=a.B3AN5.output;
  a.B3OR1.input2=a.B3AN6.output;
  
  a.B4NR1.input1=a.B4AN2.output;
  a.B4NR1.input2=a.B4AN4.output;
  a.B4OR1.input1=a.B4AN5.output;
  a.B4OR1.input2=a.B4AN6.output;
  UPDATE();
  //phase 4
  a.B1NR2.input1=a.B1OR1.output;
  a.B1NR2.input2=a.B1AN7.output;
  
  a.B2NR2.input1=a.B2OR1.output;
  a.B2NR2.input2=a.B2AN7.output;
 
  a.B3NR2.input1=a.B3OR1.output;
  a.B3NR2.input2=a.B3AN7.output;
  
  a.B4NR2.input1=a.B4OR1.output;
  a.B4NR2.input2=a.B4AN7.output;
}
public void UPDATE_CONNECTIONS_2() {
  //phase 0:
  a.B5AN1.input1=a.B1NR2.output;
  a.B5AN1.input2=a.B1NR2.output;
  a.B5AN2.input1=a.B1NR1.output;
  a.B5AN2.input2=a.B2NR2.output;
  a.B5AN3.input1=a.B1NR1.output;
  a.B5AN3.input2=a.B2NR1.output;
  a.B5AN5.input1=a.B1NR1.output;
  a.B5AN5.input2=a.B2NR1.output;
  a.B5AN6.input1=a.B3NR1.output;
  a.B5AN6.input2=a.B4NR2.output;
  a.B5AN8.input1=a.B1NR1.output;
  a.B5AN8.input2=a.B2NR1.output;
  a.B5AN9.input1=a.B3NR1.output;
  a.B5AN9.input2=a.B4NR1.output;
  a.B5AN12.input1=a.B1NR1.output;
  a.B5AN12.input2=a.B2NR1.output;
  a.B5AN13.input1=a.B3NR1.output;
  a.B5AN13.input2=a.B4NR1.output;
  
  a.B6XR1.input1=a.B1NR1.output;
  a.B6XR1.input2=a.B1NR2.output;
  a.B6AN1.input1=c.output;
  a.B6AN1.input2=a.B4NR1.output;
  a.B6AN2.input1=a.B3NR1.output;
  a.B6AN2.input2=a.B2NR1.output;
  a.B6AN5.input1=a.B3NR1.output;
  a.B6AN5.input2=a.B2NR1.output;
  a.B6AN6.input1=a.B4NR2.output;
  a.B6AN6.input2=a.B8NT1.output;
  a.B6AN8.input1=a.B2NR1.output;
  a.B6AN8.input2=a.B3NR2.output;
  a.B6AN10.input1=a.B2NR2.output;
  a.B6AN10.input2=a.B8NT1.output;
  
  a.B7XR1.input1=a.B2NR1.output;
  a.B7XR1.input2=a.B2NR2.output;
  a.B7AN1.input1=c.output;
  a.B7AN1.input2=a.B4NR1.output;
  a.B7AN2.input1=a.B3NR1.output;
  a.B7AN2.input2=a.B8NT1.output;
  a.B7AN4.input1=a.B3NR1.output;
  a.B7AN4.input2=a.B3NR2.output;
  a.B7AN6.input1=a.B3NR2.output;
  a.B7AN6.input2=a.B8NT1.output;
  
  a.B8XR1.input1=a.B3NR1.output;
  a.B8XR1.input2=a.B3NR2.output;
  a.B8XR3.input1=a.B4NR1.output;
  a.B8XR3.input2=a.B4NR2.output;
  a.B8AN1.input1=c.output;
  a.B8AN1.input2=a.B4NR1.output;
  a.B8AN3.input1=a.B4NR2.output;
  a.B8AN3.input2=a.B8NT1.output;
  a.B8ND1.input1=c.output;
  a.B8ND1.input2=a.B8NT1.output;
  UPDATE();
  //phase 1:
  a.B5AN4.input1=a.B5AN3.output;
  a.B5AN4.input2=a.B3NR2.output;
  a.B5AN7.input1=a.B5AN5.output;
  a.B5AN7.input2=a.B5AN6.output;
  a.B5AN10.input1=a.B5AN8.output;
  a.B5AN10.input2=a.B5AN9.output;
  a.B5AN14.input1=a.B5AN12.output;
  a.B5AN14.input2=a.B5AN13.output;
  
  a.B6AN3.input1=a.B6AN1.output;
  a.B6AN3.input2=a.B6AN2.output;
  a.B6AN7.input1=a.B6AN5.output;
  a.B6AN7.input2=a.B6AN6.output;
  a.B6AN9.input1=a.B6AN8.output;
  a.B6AN9.input2=a.B8NT1.output;
  
  a.B7AN3.input1=a.B7AN1.output;
  a.B7AN3.input2=a.B7AN2.output;
  a.B7AN5.input1=a.B7AN4.output;
  a.B7AN5.input2=a.B8NT1.output;
  
  a.B8AN2.input1=a.B8AN1.output;
  a.B8AN2.input2=a.B8NT1.output;
  UPDATE();
  //phase 2:
  a.B5AN11.input1=a.B5AN10.output;
  a.B5AN11.input2=c.output;
  
  a.B6AN4.input1=a.B6AN3.output;
  a.B6AN4.input2=a.B8NT1.output;
  
  a.B7OR1.input1=a.B7AN3.output;
  a.B7OR1.input2=a.B7AN5.output;
  UPDATE();
  //phase 3:
  a.B5NT1.input=a.B5AN14.output;
  UPDATE();
  //phase 4:
  a.B5OR1.input1=a.B5AN1.output;
  a.B5OR1.input2=a.B5AN2.output;
  a.B5OR2.input1=a.B5AN4.output;
  a.B5OR2.input2=a.B5AN7.output;
  
  a.B6OR1.input1=a.B6AN4.output;
  a.B6OR1.input2=a.B6AN7.output;
  a.B6OR2.input1=a.B6AN9.output;
  a.B6OR2.input2=a.B6AN10.output;
  UPDATE();
  //phase 5:
  a.B5NR1.input1=a.B5OR1.output;
  a.B5NR1.input2=a.B5OR2.output;
  
  a.B6NR1.input1=a.B6OR1.output;
  a.B6NR1.input2=a.B6OR2.output;
  
  a.B7NR1.input1=a.B7OR1.output;
  a.B7NR1.input2=a.B7AN6.output;
  
  a.B8NR1.input1=a.B8AN2.output;
  a.B8NR1.input2=a.B8AN3.output;
  UPDATE();
  //phase 6:
  a.B5NT2.input=a.B5NR1.output;
  
  a.B6XR2.input1=a.B6XR1.output;
  a.B6XR2.input2=a.B6NR1.output;
  
  a.B7XR2.input1=a.B7XR1.output;
  a.B7XR2.input2=a.B7NR1.output;
  
  a.B8XR2.input1=a.B8XR1.output;
  a.B8XR2.input2=a.B8NR1.output;
  a.B8XR4.input1=a.B8XR3.output;
  a.B8XR4.input2=a.B8ND1.output;
  UPDATE();
  //phase 7:
  a.B5OR3.input1=a.B5NT2.output;
  a.B5OR3.input2=a.B5AN11.output;
  UPDATE();
  //phase 8:
  x.input=a.B5NT1.output;
  c4.input=a.B5OR3.output;
  y.input=a.B5NR1.output;
  f3.input=a.B6XR2.output;
  f2.input=a.B7XR2.output;
  f1.input=a.B8XR2.output;
  f0.input=a.B8XR4.output;
}
public void UPDATE_INPUTS() {
  for(int i=0;i<inputC;i++) {
    allIns[i].CliUpdate();
  }
}
  public void settings() {  size(1000,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "virtcompALL.MAIN" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
