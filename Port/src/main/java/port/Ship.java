package port;

import util.Randomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {

    private List<Package> packages;
    private String name;

    public Ship(String name){
        this.name = name;
        packages = new ArrayList<>();
        int number = Randomizer.getRandomNumber(1,4);
        for (int i = 0; i < number; i++) {
            packages.add(new Package());
        }
    }

    public String getName() {
        return name;
    }

    public List<Package> getPackages(){
        return Collections.unmodifiableList(packages);
    }

}
