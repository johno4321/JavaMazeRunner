// Exercise 18.20 Solution: Maze.java
// Program traverses a maze.
import java.util.Scanner;
import java.awt.Point;
import java.io.*;

public class Maze
{
   static final int DOWN = 0;
   static final int RIGHT = 1;
   static final int UP = 2;
   static final int LEFT = 3;
   static final int X_START = 2;
   static final int Y_START = 0;
   
   static final int X_MAX = 12;
   static final int Y_MAX = 12;
   
   private int xMax = X_MAX;
   private int yMax = Y_MAX;
   
   static int move = 0;
   
   private char maze[][] =
      { { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
        { '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#' },
        { '.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#' },
        { '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#' },
        { '#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '.' },
        { '#', '#', '#', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
        { '#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
        { '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
        { '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#' },
        { '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#' },
        { '#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#' },
        { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' } };

   static Scanner scanner = new Scanner( System.in );
   
   public Maze(String inputMazePath) throws java.io.IOException
   {
		boolean firstLine = true;
		
		FileInputStream fIn = new FileInputStream(inputMazePath);  
		BufferedReader br = new BufferedReader( new InputStreamReader( fIn ) );
		StringBuffer text = new StringBuffer();
		
		String line = br.readLine();
		
		int rowCounter = 0;
		
		while(line != null)
		{
			if(firstLine)
			{
				String[] widthAndHeight = line.split(" ");
				
				xMax = Integer.parseInt(widthAndHeight[0]);
				yMax = Integer.parseInt(widthAndHeight[1]);
				
				firstLine = false;
				
				maze = new char[xMax][yMax];
				rowCounter = 0;
			}
			else
			{
				String[] elements = line.split(" ");
				
				for(int column = 0; column < elements.length; column++)
				{
					maze[rowCounter][column] = elements[column].toCharArray()[0];
				}
				
				rowCounter++;
			}
			
			line = br.readLine();
		}
	}
		   
   // method calls mazeTraversal with correct starting point and direction
   public void traverse()
   {
		boolean result = mazeTraversal( maze, X_START, Y_START );

		if ( !result )
			System.out.println( "Maze has no solution." );
		else
			System.out.println( "Maze solution found." );
   } // end method traverse

   // traverse maze recursively
   public boolean mazeTraversal(char maze2[][], int x, int y )
   {
	   printMaze();
	   	   
	   Point[] moves = getMoves(x, y);
	   
	   for ( Point potentialMove : moves ) 
	   {
		   if(isMoveValid(potentialMove.x, potentialMove.y))
		   {
				maze2[potentialMove.x][potentialMove.y] = 'X'; 
				
				//check for exit condition
				if((potentialMove.x == X_MAX - 1) || (potentialMove.y == Y_MAX - 1))
				{
					//we have a path out of the maze
					return true;
				}
				
				if(mazeTraversal(maze2, potentialMove.x, potentialMove.y))
				{
					return true;
				}
				
				maze2[potentialMove.x][potentialMove.y] = '.'; 
		   }
	   }
	   
	   return false;
			      
	   
   } // end method mazeTraversal
   
   private Point[] getMoves ( int x, int y ) 
   {
        Point[] moves = new Point[4];

        moves[0] = new Point(x + 1, y);
        moves[1] = new Point(x, y + 1);
        moves[2] = new Point(x - 1, y);
        moves[3] = new Point(x, y - 1);

        return moves;
    }
   
   private boolean isMoveValid(int nextX, int nextY)
   {
	   if(nextX < 0)
			return false;
		
		if(nextY < 0)
			return false;
		
		if(nextX >= maze.length)
			return false;
		
		if(nextY >= maze.length)
			return false;
		
		return maze[nextX][nextY] == '.';
   }
	
	// draw maze
	public void printMaze()
	{
	  // for each space in maze
      for ( int row = 0; row < maze.length; row++ )
      {
         for ( int column = 0; column < maze[ row ].length;
            column++ )
         {
            if ( maze[ row ][ column ] == '0' )
               System.out.print( " ." );
            else
               System.out.print( " " + maze[ row ][ column ] );
         }

       
         System.out.println();
      } // end for

      System.out.println();
   } // end method printMaze
} // end class Maze

/*************************************************************************
* (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/

