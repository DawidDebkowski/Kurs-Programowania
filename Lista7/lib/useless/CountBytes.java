import java.io.*;

class CountBytes {
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {

    File f = new File("InFile.txt");

    FileOutputStream fout=new FileOutputStream(f, true);    
    fout.write(65);    
    fout.close(); 


    // Zliczanie ilosci bytow w pliku  
    FileInputStream fin = new FileInputStream(f);
    int total = 0;
    while (fin.read() != -1) total++;

    System.out.println(total + " bytes");
    fin.close();
  }
}
        