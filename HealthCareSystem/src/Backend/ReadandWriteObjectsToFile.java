package Backend;

public void writeToFile(ArrayList<Item> catalog)
    {
    	try
    	{
    	
    	FileOutputStream fos = new FileOutputStream("src\\TestForInv");
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	for(int i = 0; i < catalog.size(); i++)
    		{
    			if(catalog.get(i) != null)
    			{
    			oos.writeObject(catalog.get(i));
    			}
    		}
    		oos.writeObject(0);
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void readFromFile(ArrayList<Item> catalog)
    {
    	try
    	{
    	FileInputStream fis = new FileInputStream("src\\TestForInv");
    	ObjectInputStream ois = new ObjectInputStream(fis);
    	Object input;
    	boolean cont = true;
    	while(cont)
    	{
    		input = ois.readObject();
    		if(!input.equals(0))
    		{
    			System.out.println(1);
    			catalog.add((Item)input);
    		}
    		else
    		{
    			cont = false;
    		}
    	
    	}
    	
    	
    	}
    	catch(IOException | ClassNotFoundException e)
    	{
    		e.printStackTrace();
    	
    }
    }
