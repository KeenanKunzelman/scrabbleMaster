package csc312;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ScrabbleMasterTest {
    @Test
    public void testValidURL() {

        ScrabbleMaster scrabbleMaster = new ScrabbleMaster();
        assertEquals( new Character('E'), scrabbleMaster.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=1&pos=b5" ) );

    }

    @Test
    public void testServerError() {

        ScrabbleMaster scrabbleMaster = new ScrabbleMaster();
        assertEquals( null, scrabbleMaster.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z99" ) );

    }

    @Test
    public void testServerForbidden() {

        ScrabbleMaster scrabbleMaster = new ScrabbleMaster();
        assertEquals( null, scrabbleMaster.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z88" ) );

    }

    @Test
    public void testInvalidDomain() {
        ScrabbleMaster scrabbleMaster = new ScrabbleMaster();

        assertEquals( null, scrabbleMaster.getURL( "https://aninvaliddomainname.com" ) );
    }


    @Test
    public void testGrid(){
        ArrayList<String> gridStr = new ArrayList<>(Arrays.asList("aBCDa", "BbBbE", "BBcbB", "EbZeE", "aEEeZ", "aBBEa", "BbBbE", "CBcZE", "Dbbee", "aEBEZ"));
        ScrabbleMaster scrabbleMaster = new ScrabbleMaster();
        assertEquals(gridStr, scrabbleMaster.setGrid(1));
    }
}