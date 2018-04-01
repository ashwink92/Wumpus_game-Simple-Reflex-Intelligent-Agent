/*
 * Class that defines the agent function.
 * 
 * Written by James P. Biagioni (jbiagi1@uic.edu)
 * for CS511 Artificial Intelligence II
 * at The University of Illinois at Chicago
 * 
 * Last modified 2/19/07 
 * 
 * DISCLAIMER:
 * Elements of this application were borrowed from
 * the client-server implementation of the Wumpus
 * World Simulator written by Kruti Mehta at
 * The University of Texas at Arlington.
 * 
 */

import java.util.Random;

class AgentFunction {
	
	// string to store the agent's name
	// do not remove this variable
	private String agentName = "Agent Smith";
	
	// all of these variables are created and used
	// for illustration purposes; you may delete them
	// when implementing your own intelligent agent
	private int[] actionTable;
	private boolean bump;
	private boolean glitter;
	private boolean breeze;
	private boolean stench;
	private boolean scream;
	private Random rand;

	public AgentFunction()
	{
		// for illustration purposes; you may delete all code
		// inside this constructor when implementing your 
		// own intelligent agent

		// this integer array will store the agent actions
		actionTable = new int[8];
				  
		actionTable[0] = Action.GO_FORWARD;
		actionTable[1] = Action.TURN_RIGHT;
		actionTable[2] = Action.TURN_LEFT;
		actionTable[3] = Action.SHOOT;
		actionTable[4] = Action.GRAB;
		actionTable[5] = Action.NO_OP;
		// new random number generator, for
		// randomly picking actions to execute
		rand = new Random();
	}

	public int process(TransferPercept tp)
	{
		// To build your own intelligent agent, replace
		// all code below this comment block. You have
		// access to all percepts through the object
		// 'tp' as illustrated here:
		
		// read in the current percepts
		bump = tp.getBump();
		glitter = tp.getGlitter();
		breeze = tp.getBreeze();
		stench = tp.getStench();
		scream = tp.getScream();

		/* Conditions for Actions
		 1. If glitter is true - Action Grab
		 2. Else if stench is true - Either Shoot or Don't do anything(No-op) - (With Equal Probability 50-50)
				 -- Since cost of getting caught by Wumpus is very high (Better than taking a chance)
		 3. Else if Breeze is true - No-Op
		 		-- Since cost of getting caught by Wumpus is very high (Better than taking a chance)
		 4. Else if Bump is true - Turn Left or Right (With Equal Probability 50-50)
		 		-- Since you've hit the wall
		 5. Else
		 		-- Either Move Forward or Turn Left or Right (With Equal Probability 0.33 for each)
		*/

		if (glitter == true) {
			return actionTable[4];
		} else if(stench == true) {
			switch(rand.nextInt(2)) {
				case 0:
					return actionTable[3];
					//return actionTable[rand.nextInt(3)+1];
				case 1:
					return actionTable[5];
				default:
					return actionTable[rand.nextInt(3)+1];
			}
		} else if(breeze == true) {
			return actionTable[5];
		} else if (bump == true) {
			return actionTable[rand.nextInt(2)+1];
		} else {
			return actionTable[rand.nextInt(3)];
		}

	/*
		Glitter	Stench	Breeze	Bump	Scream	Action
1	1	1	1	1	Grab
1	1	1	1	0	Grab
1	1	1	0	1	Grab
1	1	1	0	0	Grab
1	1	0	1	1	Grab
1	1	0	1	0	Grab
1	1	0	0	1	Grab
1	1	0	0	0	Grab
1	0	1	1	1	Grab
1	0	1	1	0	Grab
1	0	1	0	1	Grab
1	0	1	0	0	Grab
1	0	0	1	1	Grab
1	0	0	1	0	Grab
1	0	0	0	1	Grab
1	0	0	0	0	Grab
0	1	1	1	1	Shoot - 0.5, No Action - 0.5
0	1	1	1	0	Shoot - 0.5, No Action - 0.5
0	1	1	0	1	Shoot - 0.5, No Action - 0.5
0	1	1	0	0	Shoot - 0.5, No Action - 0.5
0	1	0	1	1	Shoot - 0.5, No Action - 0.5
0	1	0	1	0	Shoot - 0.5, No Action - 0.5
0	1	0	0	1	Shoot - 0.5, No Action - 0.5
0	1	0	0	0	Shoot - 0.5, No Action - 0.5
0	0	1	1	1	No Action - 0.5
0	0	1	1	0	No Action - 0.5
0	0	1	0	1	No Action - 0.5
0	0	1	0	0	No Action - 0.5
0	0	0	1	1	Turn Left - 0.5, Turn Right - 0.5
0	0	0	1	0	Turn Left - 0.5, Turn Right - 0.5
0	0	0	0	1	Go Forward - 0.33, Turn Left - 0.33, Turn Right - 0.33
0	0	0	0	0	Go Forward - 0.33, Turn Left - 0.33, Turn Right - 0.33
	 */


	}
	
	// public method to return the agent's name
	// do not remove this method
	public String getAgentName() {
		return agentName;
	}
}