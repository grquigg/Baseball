package Baseball;


/**
 * Write a description of class FileModifier here.
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.*;
public class FileModifier
{
    public static void main (String [] args) throws Exception
    {
        Scanner reader = new Scanner(System.in);
        
        XSSFWorkbook workbook = new XSSFWorkbook("teams.xlsx");
        int number = workbook.getNumberOfSheets();
        
        ArrayList<Team> list = new ArrayList<Team>();
        FileInputStream fIS = new FileInputStream("teams.xlsx");
        XSSFRow row;
        
        for (int i = 0; i < number; i++)
        {
            XSSFSheet spreadsheet = workbook.getSheetAt(i);
            list.add(new Team(spreadsheet.getRow(0).getCell(0).getStringCellValue(), i));
            Iterator < Row > rowIterator = spreadsheet.iterator();
            int indexSplit = 0;
            int playerIndex = 0;
            int pitcherIndex = 0;
            int rowIndex = 0;
            //nullspace can help
            while (rowIterator.hasNext())
            {
                row = (XSSFRow) rowIterator.next();
                System.out.println("Path");
                boolean Switch = false;
                if (rowIndex != 0)
                {
                    if (row.getCell(0).getCellType() != CellType.BLANK && indexSplit == 0)
                    {
                        list.get(i).addPlayer(row.getCell(0).getStringCellValue(), "Blank");
                        for (int j = 1; j < 12; j++)
                        {
                            int num = (int) row.getCell(j).getNumericCellValue();
                            list.get(i).getPlayer(playerIndex).setStat(j-1, num);
                        }
                        playerIndex++;
                        System.out.println("Path A");
                    }
                        else if (row.getCell(0).getCellType() == CellType.BLANK)
                        {
                            indexSplit = rowIndex;
                            System.out.println("Path C");
                        }
                    if (indexSplit != 0)
                    {
                        if (Switch == true)
                        {
                            indexSplit += 1;
                            list.get(i).addToRotation(new Pitcher(row.getCell(0).getStringCellValue(), "Pitcher"));
                            for (int z = 1; z < 12; z++)
                            {
                                int numeral = (int) row.getCell(z).getNumericCellValue();
                                list.get(i).getPitcher(pitcherIndex).setPitchStat(z-1, numeral);
                            }
                            System.out.println("Path B");
                            pitcherIndex++;
                        }
                            else
                                Switch = true;
                    }
                }
                rowIndex++;
            }
            System.out.println("Index Split");
            System.out.println(indexSplit);
        }
        list.get(0).displayTeamStats();
        
        list.add(new Team("Cubs1"));
        File newFile = new File("teams.xlsx");
        XSSFWorkbook work = new XSSFWorkbook();
        
        list.get(1).addPlayer("Anthony Rizzo", "First Baseman");
        list.get(1).addPlayer("Kris Bryant", "Third Baseman");
        int count = list.size();
        int nPlayers;
        int nIndexSplit;
        int nPitchers;
        System.out.println(count);
        XSSFRow rowTwo;
        for (int j = 0; j < count; j++)
        {
            System.out.println("Okay");
            XSSFSheet newSheet = work.createSheet(list.get(j).getTeamName());
            nPlayers = list.get(j).returnRoster() - 1;
            nPitchers = list.get(j).returnNumberOfPitchers();
            int rowid = 0;
            rowTwo = newSheet.createRow(rowid);
            for (int i = 0; i < 13; i++)
            {
                rowTwo.createCell(i);
            }
            rowTwo.getCell(0).setCellValue(list.get(j).getTeamName());
            rowTwo.getCell(1).setCellValue("G");
            rowTwo.getCell(2).setCellValue("H");
            rowTwo.getCell(3).setCellValue("AB");
            rowTwo.getCell(4).setCellValue("2B");
            rowTwo.getCell(5).setCellValue("3B");
            rowTwo.getCell(6).setCellValue("HR");
            rowTwo.getCell(7).setCellValue("RBI");
            rowTwo.getCell(8).setCellValue("SO");
            rowTwo.getCell(9).setCellValue("GO");
            rowTwo.getCell(10).setCellValue("FO");
            rowTwo.getCell(11).setCellValue("BB");
            rowTwo.getCell(12).setCellValue("AVG");
            rowid++;
            for (int k = 0; k < nPlayers; k++)
            {
                rowTwo = newSheet.createRow(rowid);
                for (int m = 0; m < 13; m++)
                {
                    rowTwo.createCell(m);
                }
                rowTwo.getCell(0).setCellValue(list.get(j).getPlayer(k).getName());
                for (int n = 1; n < 12; n++)
                {
                    rowTwo.getCell(n).setCellValue(list.get(j).getPlayer(k).getStat(n-1));
                }
                rowTwo.getCell(12).setCellValue(list.get(j).getPlayer(k).getAvg());
                rowid++;
            }
            nIndexSplit = rowid;
            rowTwo = newSheet.createRow(nIndexSplit);
            for (int n = 0; n < 14; n++)
            {
                rowTwo.createCell(n);
            }
            
            rowTwo.getCell(1).setCellValue("G");
            rowTwo.getCell(2).setCellValue("GS");
            rowTwo.getCell(3).setCellValue("W");
            rowTwo.getCell(4).setCellValue("L");
            rowTwo.getCell(5).setCellValue("SV");
            rowTwo.getCell(6).setCellValue("ER");
            rowTwo.getCell(7).setCellValue("H");
            rowTwo.getCell(8).setCellValue("SO");
            rowTwo.getCell(9).setCellValue("BB");
            rowTwo.getCell(10).setCellValue("FO");
            rowTwo.getCell(11).setCellValue("GO");
            rowTwo.getCell(12).setCellValue("IP");
            rowTwo.getCell(13).setCellValue("ERA");
            rowid++;
            for (int s = 0; s < nPitchers; s++)
            {
                rowTwo = newSheet.createRow(rowid++);
                for (int m = 0; m < 14; m++)
                {
                    rowTwo.createCell(m);
                }
                rowTwo.getCell(0).setCellValue(list.get(j).getPitcher(s).getName());
                for (int k = 1; k < 12; k++)
                {
                    rowTwo.getCell(k).setCellValue(list.get(j).getPitcher(s).getPitchStat(k-1));
                }
            }
        }
        FileOutputStream out = new FileOutputStream("teams.xlsx");
        work.write(out);
        out.close();
        System.out.println("File Modified");
    }
}
