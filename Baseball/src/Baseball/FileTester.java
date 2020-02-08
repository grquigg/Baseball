package Baseball;


/**
 * Write a description of class File here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.*;

public class FileTester
{
    public static void main (String [] args) throws Exception
    {
        Scanner reader = new Scanner(System.in);
        File newFile = new File("teams.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        Team teamA = new Team("White Sox");
        teamA.addPlayer("Tyler Flowers", "Catcher");
        teamA.addPlayer("Paul Konerko", "First Baseman");
        teamA.addPlayer("Carlos Rodon", "Pitcher");
        teamA.addToRotation(new Pitcher("Carlos Rodon", "Pitcher"));
        int size = teamA.returnRoster() - 1;
        XSSFSheet sheet = workbook.createSheet("White Sox");
        int pitchingSize = teamA.returnNumberOfPitchers();
        XSSFRow row;
        row = sheet.createRow(0);
        int rowid = 0;
        for (int i = 0; i < 13; i++)
        {
            row.createCell(i);
        }
        row.getCell(0).setCellValue(teamA.getTeamName());
        row.getCell(1).setCellValue("G");
        row.getCell(2).setCellValue("H");
        row.getCell(3).setCellValue("AB");
        row.getCell(4).setCellValue("2B");
        row.getCell(5).setCellValue("3B");
        row.getCell(6).setCellValue("HR");
        row.getCell(7).setCellValue("RBI");
        row.getCell(8).setCellValue("SO");
        row.getCell(9).setCellValue("GO");
        row.getCell(10).setCellValue("FO");
        row.getCell(11).setCellValue("BB");
        row.getCell(12).setCellValue("AVG");
        rowid++;
        teamA.getPlayer(0).increment(2);
        teamA.getPlayer(0).increment(2);
        teamA.getPlayer(0).increment(1);
        for (int i = 0; i < size; i++)
        {
            row = sheet.createRow(rowid++);
            for (int j = 0; j < 13; j++)
            {
                row.createCell(j);
            }
            row.getCell(0).setCellValue(teamA.getPlayer(i).getName());
            for (int k = 1; k < 12; k++)
            {
                row.getCell(k).setCellValue(teamA.getPlayer(i).getStat(k-1));
            }
            row.getCell(12).setCellValue(teamA.getPlayer(i).getAvg());
        }
        row = sheet.createRow(rowid);
        for (int i = 0; i < 14; i++)
        {
            row.createCell(i);
        }
        row.getCell(1).setCellValue("G");
        row.getCell(2).setCellValue("GS");
        row.getCell(3).setCellValue("W");
        row.getCell(4).setCellValue("L");
        row.getCell(5).setCellValue("SV");
        row.getCell(6).setCellValue("ER");
        row.getCell(7).setCellValue("H");
        row.getCell(8).setCellValue("SO");
        row.getCell(9).setCellValue("BB");
        row.getCell(10).setCellValue("FO");
        row.getCell(11).setCellValue("GO");
        row.getCell(12).setCellValue("IP");
        row.getCell(13).setCellValue("ERA");
        rowid++;
        for (int i = 0; i < pitchingSize; i++)
        {
            row = sheet.createRow(rowid++);
            for (int j = 0; j < 14; j++)
            {
                row.createCell(j);
            }
            row.getCell(0).setCellValue(teamA.getPitcher(i).getName());
            for (int k = 1; k < 12; k++)
            {
                row.getCell(k).setCellValue(teamA.getPitcher(i).getPitchStat(k-1));
            }
        }
        FileOutputStream out = new FileOutputStream("teams.xlsx");
        workbook.write(out);
        out.close();
        System.out.println(rowid);
        
    }
}
