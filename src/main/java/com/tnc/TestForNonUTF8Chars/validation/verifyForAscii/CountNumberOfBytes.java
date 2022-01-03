package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

public class CountNumberOfBytes {

    private static final int CHAR_ONE_BYTE_MASK = 0xFFFFFF80;

    private static final int CHAR_TWO_BYTES_MASK = 0xFFFFF800;

    private static final int CHAR_THREE_BYTES_MASK = 0xFFFF0000;

    private static final int CHAR_FOUR_BYTES_MASK = 0xFFE00000;

    private static final int CHAR_FIVE_BYTES_MASK = 0xFC000000;

    private static final int CHAR_SIX_BYTES_MASK = 0x80000000;

    public static final int countBytes( char[] chars )
    {
        if ( chars == null )
        {
            return 0;
        }

        int nbBytes = 0;
        int currentPos = 0;

        while ( currentPos < chars.length )
        {
            int nbb = countNbBytesPerChar( chars[currentPos] );

            // If the number of bytes necessary to encode a character is
            // above 3, we will need two UTF-16 chars
            currentPos += ( nbb < 4 ? 1 : 2 );
            nbBytes += nbb;
        }

        return nbBytes;
    }
    public static final int countNbBytesPerChar( char chars )
    {
        if ( ( chars & CHAR_ONE_BYTE_MASK ) == 0 )
        {
            return 1;
        }
        else if ( ( chars & CHAR_TWO_BYTES_MASK ) == 0 )
        {
            return 2;
        }
        else if ( ( chars & CHAR_THREE_BYTES_MASK ) == 0 )
        {
            return 3;
        }
        else if ( ( chars & CHAR_FOUR_BYTES_MASK ) == 0 )
        {
            return 4;
        }
        else if ( ( chars & CHAR_FIVE_BYTES_MASK ) == 0 )
        {
            return 5;
        }
        else if ( ( chars & CHAR_SIX_BYTES_MASK ) == 0 )
        {
            return 6;
        }
        else
        {
            return -1;
        }
    }
}
