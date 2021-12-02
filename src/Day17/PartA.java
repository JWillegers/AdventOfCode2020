package Day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartA {
    private int nOfLines = 8;
    private BufferedReader reader;
    private List<Coordinate> coordinates;
    private List<Coordinate> copy;
    private int maxXY;
    private int maxZ;



    public static void main(String[] args) {
        PartA part = new PartA();
        part.setup();
        part.solution();
    }

    public PartA() {}

    public PartA(PartA partA) {
        this.coordinates = new ArrayList<>();
        for (Coordinate cord : partA.coordinates) {
            this.coordinates.add(new Coordinate(cord));
        }
    }

    public void setup() {
        try {
            maxZ = 0;
            maxXY = nOfLines;
            this.coordinates = new ArrayList<>();
            reader = new BufferedReader(new FileReader("src/Day17/input.txt"));
            for (int i = 0; i < nOfLines; i++) {
                String line = reader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    Coordinate cord = new Coordinate(j - (line.length() / 2), i - (nOfLines / 2), 0, line.charAt(j) == '#');
                    this.coordinates.add(cord);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }



    public void solution() {
        for (int i = 0; i < 6; i++) {
            if (maxZ == maxXY) {
                System.out.println("TODO: implement this");
            } else {
                maxZ++;
                for (int j = -4; j < maxXY - 4; j++) {
                    for (int k = -4; k < maxXY - 4; k++) {
                        Coordinate coordinate1 = new Coordinate(j, k, maxZ, false);
                        Coordinate coordinate2 = new Coordinate(j, k, -maxZ, false);
                        this.coordinates.add(coordinate1);
                        this.coordinates.add(coordinate2);
                    }
                }
            }

            PartA copy = new PartA(this);
            for (int cord = 0; cord < this.coordinates.size(); cord++) {
                int neighbours = calculateAmountOfNeighbours(copy.coordinates.get(cord), copy);
                if (copy.coordinates.get(cord).active && !(neighbours == 2 || neighbours == 3)) {
                    this.coordinates.get(cord).active = false;
                } else if (!copy.coordinates.get(cord).active && neighbours == 3) {
                    this.coordinates.get(cord).active = true;
                }
            }
            if (i != 5) {
                System.out.println("\n---------- NEW ITERATION ---------- \n");
            }

        }

        int sum = 0;
        for (Coordinate cord : coordinates) {
            if (cord.active) {
                sum++;
            }
        }

        System.out.println("\n\n\nactive cubes: " + sum);
    }

    public int calculateAmountOfNeighbours(Coordinate cord, PartA part) {
        int neighbours = 0;
        System.out.println("\nNow testing for cords: x="
                + cord.x + ", y=" + cord.y + ", z=" + cord.z);
        for (int ix = cord.x - 1; ix < cord.x + 2; ix++) {
            for (int iy = cord.y - 1; iy < cord.y + 2; iy++) {
                for (int iz = cord.z - 1; iz < cord.z + 2; iz++) {
                    for (int i = 0; i < part.coordinates.size(); i++) {
                        Coordinate cordA = part.coordinates.get(i);
                        if (!(ix == cord.x && iy == cord.y && iz == cord.z)) {
                            if (cordA.x == ix && cordA.y == iy && cordA.z == iz && cordA.active) {
                                System.out.println("x=" + ix + " y=" + iy + " z=" + iz);
                                neighbours++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("amount of neigbours: " + neighbours);

        return neighbours;
    }
}
