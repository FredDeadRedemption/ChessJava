import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RNGBot {

    public static int getRandomMove(List<Integer> list){

        List<Integer> nonEmptyIndices = new ArrayList<>();

        // determine non-empty indices
        for(int i = 0; i < list.size(); i++){
            if (list.get(i) != null && list.get(i) != 0) {
                nonEmptyIndices.add(i);
            }
        }

        Random random = new Random();

        // Generate a random index from the list of non-empty indices
        return nonEmptyIndices.get(random.nextInt(nonEmptyIndices.size()));
    }
    public static int getRandomNonEmptyListIndex(List<List<Integer>> list){

        System.out.println("input list" + list);

        List<Integer> nonEmptyIndices = new ArrayList<>();

        // determine index of lists with non-empty indices
        for(int i = 0; i < list.size(); i++){
            List<Integer> sublist = list.get(i);
            if (!sublist.isEmpty()){
                nonEmptyIndices.add(i);
            }
        }

        Random random = new Random();

        // Generate a random index from the list of non-empty indices
        return nonEmptyIndices.get(random.nextInt(nonEmptyIndices.size()));
    }
}
