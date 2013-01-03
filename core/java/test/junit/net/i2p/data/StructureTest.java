package net.i2p.data;
/*
 * free (adj.): unencumbered; not under the control of others
 * Written by jrandom in 2003 and released into the public domain 
 * with no warranty of any kind, either expressed or implied.  
 * It probably won't make your computer catch on fire, or eat 
 * your children, but it might.  Use at your own risk.
 *
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;

/**
 * Utility class for wrapping data structure tests
 *
 * @author jrandom
 */

public abstract class StructureTest extends TestCase{
    
    /** create a populated structure for writing */
    public abstract DataStructure createDataStructure() throws DataFormatException;

    /** create an unpopulated structure for reading */
    public abstract DataStructure createStructureToRead();
    
    public void testStructure() throws Exception{
        byte[] temp = null;
        
        DataStructure orig;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        orig = createDataStructure();
        orig.writeBytes(baos);
        
        
        temp = baos.toByteArray();
        
        DataStructure ds;
        ByteArrayInputStream bais = new ByteArrayInputStream(temp);
        
        ds = createStructureToRead();
        ds.readBytes(bais);
        

        // Not all classes implement equals, so write out again
        //assertEquals(orig, ds);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ds.writeBytes(baos2);
        byte[] temp2 = baos2.toByteArray();
        assert(DataHelper.eq(temp, temp2));
    }
    
}
