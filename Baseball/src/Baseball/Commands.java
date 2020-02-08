package Baseball;


/**
 * Write a description of class Commands here.
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

public class Commands
{
    private String text;
    public Commands()
    {
    }
    public void addTeam() throws Exception
    {
        XSSFWorkbook workbook = new XSSFWorkbook("teams.xlsx");
        int number = workbook.getNumberOfSheets();
        Scanner reader = new Scanner(System.in);
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
            boolean Switch = false;
            while (rowIterator.hasNext())
            {
                row = (XSSFRow) rowIterator.next();
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
                    }
                        else if (row.getCell(0).getCellType() == CellType.BLANK)
                        {
                            indexSplit = rowIndex;
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
                            pitcherIndex++;
                        }
                            else
                                Switch = true;
                    }
                }
                rowIndex++;
            }
        }
        String teamName;
        System.out.println("Name of team you would like to enter:");
        teamName = reader.nextLine();
        list.add(new Team(teamName));
        File newFile = new File("teams.xlsx");
        XSSFWorkbook work = new XSSFWorkbook();
        list.get(number).addPlayerRosterPrompt();
        list.get(number).addPitcherRosterPrompt();
       
        int count = list.size();
        int nPlayers;
        int nIndexSplit;
        int nPitchers;
        XSSFRow rowTwo;
        for (int j = 0; j < count; j++)
        {
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
            for (int nm = 1; nm < 13; nm++)
            {
                rowTwo.getCell(nm).setCellValue(list.get(j).hitterStats[nm - 1]);
            }
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
            
            for (int mn = 1; mn < 14; mn++)
            {
                rowTwo.getCell(mn).setCellValue(list.get(j).pitcherStats[mn - 1]);
            }
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
    public void rewriteFile() throws Exception
    {
        File fs = new File("teams.xlsx");
        XSSFWorkbook work = new XSSFWorkbook();
        FileOutputStream out = new FileOutputStream(fs);
        work.write(out);
        out.close();
    }
    public void listCommands()
    {
        System.out.println(1 + "\tStart New Game");
        System.out.println(2 + "\tAdd New Team");
        System.out.println(3 + "\tAdd Players to existing team");
        System.out.println(4 + "\tAdd Pitchers to existing team");
        System.out.println(5 + "\tClear File");
    }
}
