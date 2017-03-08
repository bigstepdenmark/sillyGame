package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sillygame.Dice;
import sillygame.DiceGame;
import sillygame.Printer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by ismailcam on 08/03/2017.
 */
@RunWith( MockitoJUnitRunner.class )
public class DiceGameTest
{
    @Mock Printer printer;
    @Mock Dice d1;
    @Mock Dice d2;

    @Test( expected = Exception.class )
    public void testExceptionAfterThreeThrows() throws Exception
    {
        Dice d2 = mock( Dice.class );
        DiceGame game = new DiceGame( printer, d1, d2 );
        game.roll();
        game.roll();
        game.roll();
        game.roll();
    }

    @Test( expected = Exception.class )
    public void testResetAfterNewGame() throws Exception
    {
        Dice d2 = mock( Dice.class );
        DiceGame game = new DiceGame( printer, d1, d2 );
        game.roll();
        game.roll();
        game.roll();
        game.startGame();
        game.roll();
        assertTrue( true );
    }

    @Test
    public void testGame() throws Exception
    {
        //Dice d2 = mock( Dice.class );

        when( d1.roll() ).thenReturn( 2 ).thenReturn( 4 ).thenReturn( 5 );
        when( d2.roll() ).thenReturn( 2 ).thenReturn( 3 ).thenReturn( 5 );

        DiceGame game = new DiceGame( printer, d1, d2 );
        game.setPlayer( "Kurt" );

        int res = game.roll(); //When
        assertThat( res, is( 4 ) ); //Then
        res = game.roll(); //When
        assertThat( res, is( 7 ) ); //Then
        res = game.roll(); //When
        assertThat( res, is( 10 ) ); //Then

        verify( printer, times( 1 ) ).print( anyString() );
    }
}