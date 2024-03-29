package com.thrus.test;

public final class ImprovedNoise {
    //private float hs;
    private static double time=0;
    static float yy;
    public static float hs(){
        time += 0.002;

        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                double dx = (double) x / 10;
                double dy = (double) y / 10;
                int frequency = 6;
                double noise =ImprovedNoise.noise((dx * time) + time, (dy * time) + time, time);
                yy = (float)(1-noise) /2;





            }
        }
        return yy;

    }



    static public double noise(double x, double y, double z) {
        int X = (int)Math.floor(x) & (256)-1,                  // FIND UNIT CUBE THAT
            Y = (int)Math.floor(y) & (256)-1,                  // CONTAINS POINT.
            Z = (int)Math.floor(z) & (256)-1;
        x -= Math.floor(x);                                // FIND RELATIVE X,Y,Z
        y -= Math.floor(y);                                // OF POINT IN CUBE.
        z -= Math.floor(z);
        double u = fade(x),                                // COMPUTE FADE CURVES
            v = fade(y),                                // FOR EACH OF X,Y,Z.
            w = fade(z);
        int A = p[X]+Y, AA = p[A]+Z, AB = p[A+1]+Z,      // HASH COORDINATES OF
            B = p[X+1]+Y, BA = p[B]+Z, BB = p[B+1]+Z;      // THE 8 CUBE CORNERS,

        return lerp(w, lerp(v, lerp(u, grad(p[AA  ], x  , y  , z   ),  // AND ADD
                                    grad(p[BA  ], x-1, y  , z   )), // BLENDED
                            lerp(u, grad(p[AB  ], x  , y-1, z   ),  // RESULTS
                                 grad(p[BB  ], x-1, y-1, z   ))),// FROM  8
                    lerp(v, lerp(u, grad(p[AA+1], x  , y  , z-1 ),  // CORNERS
                                 grad(p[BA+1], x-1, y  , z-1 )), // OF CUBE
                         lerp(u, grad(p[AB+1], x  , y-1, z-1 ),
                              grad(p[BB+1], x-1, y-1, z-1 ))));
    }
    static double fade(double t) { return t * t * t * (t * (t * 6 - 15.001) + 10); }
    static double lerp(double t, double a, double b) { return a + t * (b - a); }
    static double grad(int hash, double x, double y, double z) {
        int h = hash & 15;                      // CONVERT LO 4 BITS OF HASH CODE
        double u = h<7 ? x : y,                 // INTO 12 GRADIENT DIRECTIONS.
            v = h<4 ? y : h==12||h==14 ? x : z;
        return ((h&1) == 0 ? u : -u) + ((h&2) == 0 ? v : -v);
    }
    static final int p[] = new int[512], permutation[] = {208, 133, 220, 151, 200, 250, 168, 286, 113, 185, 199, 285, 159, 239, 203, 123, 257, 221, 207, 147, 238, 95, 295, 188, 160, 305, 58, 105, 272, 87, 246, 176, 161, 248, 218, 195, 237, 55, 72, 241, 175, 62, 236, 229, 140, 179, 273, 94, 114, 232, 121, 212, 209, 126, 109, 265, 282, 68, 274, 65, 202, 124, 57, 139, 234, 299, 83, 158, 206, 279, 296, 264, 142, 66, 146, 125, 157, 226, 54, 166, 111, 174, 102, 116, 76, 284, 204, 277, 90, 63, 53, 156, 74, 80, 278, 110, 106, 189, 172, 69, 240, 186, 104, 254, 130, 101, 52, 270, 154, 184, 136, 60, 194, 231, 288, 91, 64, 77, 100, 281, 70, 230, 96, 223, 301, 173, 262, 228, 112, 260, 216, 180, 205, 187, 93, 249, 196, 211, 162, 256, 148, 210, 143, 251, 294, 163, 50, 144, 145, 119, 99, 98, 135, 303, 191, 73, 275, 193, 128, 150, 181, 255, 304, 56, 118, 134, 171, 289, 269, 267, 297, 244, 141, 268, 283, 61, 169, 152, 85, 107, 219, 115, 51, 170, 253, 92, 155, 182, 271, 67, 88, 183, 103, 214, 300, 178, 84, 78, 233, 164, 213, 201, 252, 81, 259, 177, 235, 276, 287, 82, 217, 192, 263, 197, 79, 227, 291, 149, 132, 302, 225, 129, 247, 258, 165, 198, 298, 122, 127, 75, 215, 131, 290, 224, 293, 89, 280, 243, 108, 190, 138, 266, 120, 137, 86, 292, 71, 261, 59, 153, 245, 117, 242, 167, 97, 222}
        ;}
        //251, 34, 255, 255, 255, 255, 134, 255, 130, 255, 135, 255, 255, 48, 225, 193, 160, 125, 255, 255, 204, 255, 255, 255, 255, 255, 154, 255, 255, 149, 255, 255, 145, 255, 111, 89, 50, 255, 123, 113, 205, 255, 4, 255, 255, 255, 255, 235, 8, 2, 255, 186, 252, 255, 255, 166, 137, 150, 23, 255, 255, 255, 255, 255, 9, 255, 139, 174, 255, 255, 255, 78, 55, 71, 255, 17, 255, 255, 219, 221, 242, 255, 255, 57, 128, 253, 255, 255, 255, 18, 255, 216, 255, 255, 255, 255, 33, 255, 196, 255, 255, 133, 207, 58, 177, 255, 255, 210, 206, 255, 255, 255, 237, 255, 77, 255, 255, 255, 255, 245, 12, 40, 255, 170, 255, 255, 110, 191, 144, 244, 255, 222, 117, 255, 255, 255, 255, 140, 255, 255, 233, 255, 255, 255, 255, 255, 211, 226, 255, 213, 255, 92, 209, 5, 255, 241, 255, 255, 255, 190, 255, 167, 255, 215, 255, 255, 255, 159, 192, 142, 155, 255, 185, 95, 11, 255, 179, 54, 255, 234, 182, 72, 255, 6, 98, 255, 100, 28, 3, 240, 64, 255, 255, 73, 255, 255, 146, 127, 255, 138, 195, 88, 136, 65, 201, 202, 231, 255, 164, 255, 119, 255, 203, 131, 161, 45, 255, 56, 227, 112, 107, 184, 255, 24, 118, 255, 255, 19, 121, 152, 255, 94, 255, 158, 51, 255, 52, 255, 20, 255, 246, 223, 229, 35, 16, 255, 255, 248, 255, 1, 120, 255, 255, 255, 82, 255, 255, 194, 255, 255, 26, 175, 255, 255, 103, 255, 86, 255, 255, 255, 122, 255, 109, 255, 255, 15, 254, 255, 171, 255, 105, 255, 255, 255, 255, 53, 151, 108, 255, 255, 199, 255, 236, 255, 255, 66, 83, 255, 255, 189, 255, 255, 14, 212, 172, 36, 255, 255, 255, 255, 85, 255, 47, 255, 90, 63, 37, 41, 181, 197, 115, 148, 255, 116, 255, 255, 70, 255, 255, 44, 46, 208, 255, 176, 114, 255, 10, 255, 76, 255, 255, 255, 255, 80, 255, 214, 255, 255, 255, 255, 84, 255, 141, 255, 168, 232, 217, 250, 162, 104, 255, 255, 255, 255, 255, 255, 188, 255, 255, 239, 27, 255, 30, 255, 255, 32, 31, 255, 255, 106, 255, 255, 255, 220, 143, 49, 255, 255, 255, 255, 68, 183, 255, 129, 21, 255, 255, 255, 228, 42, 0, 255, 255, 255, 22, 99, 255, 38, 255, 7, 157, 97, 255, 255, 75, 255, 255, 101, 255, 255, 255, 255, 255, 255, 29, 255, 255, 224, 124, 255, 255, 180, 173, 69, 178, 255, 187, 91, 96, 255, 61, 62, 255, 255, 255, 255, 200, 255, 218, 255, 67, 255, 243, 249, 169, 255, 247, 255, 255, 43, 255, 255, 255, 60, 255, 81, 255, 163, 255, 255, 255, 255, 255, 156, 255, 255, 132, 255, 255, 255, 255, 93, 255, 255, 255, 255, 255, 255, 255, 255, 79, 87, 255, 165, 13, 238, 126, 198, 147, 255, 230, 255, 153, 74, 255, 39, 102, 255, 25, 59, 255, 255
        
    
	
