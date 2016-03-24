package com.inin.domain.bank.util;

import java.util.Random;

/**
 * Created by leroy on 23/3/16.
 *
 * contains some utility functions that can be used system wide
 */
public class Util {

    public static int generateRandom(){
        return new Random().nextInt(99999);
    }
}
