package com.thrus.test;

public class lehmer {
    private static int nProcGen=0;
    
    
    public lehmer(int x, int y){
        nProcGen = (x) << 16 | (y );
    }
    
    public static int rnd()
    {
        nProcGen += 0xe120fc15;
        int tmp;
        tmp = nProcGen * 0x4a39b70d;
        int m1 = (tmp >> 32) ^ tmp;
        tmp =m1 * 0x12fad5c9;
        int m2 = (tmp >> 32) ^ tmp;
        return m2;
	}
    
    double rndDouble(double min, double max)
    {
        return ((double)rnd() / (double)(0x7FFFFFFF)) * (max - min) + min;
    }

    int rndInt(int min, int max)
    {
        return (rnd() % (max - min)) + min;
	}
    
}
