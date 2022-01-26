package List;

import Entity.Branch;

public class BranchList {
    private Branch[] list;
    private int emptyHomeIndex;

    public BranchList() {
        list = new Branch[1000];
        emptyHomeIndex = 0;
    }

    public void add(Branch value) {
        list[emptyHomeIndex] = value;
        emptyHomeIndex++;
    }

    public Branch get(int index) {
        return list[index];
    }


    public Boolean isEmpty() {
        return emptyHomeIndex == 0;
    }

    public int size() {
        return emptyHomeIndex;
    }

    public void add(int index, Branch value) {
        // Check: Index invalid
        for (int i = emptyHomeIndex; i > index ; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        emptyHomeIndex++;
    }

    public void addAll(Branch[] values) {
        for (Branch v: values) {
            add(v);
        }
    }

    public void showList() {
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] != null){
                System.out.print(list[i].getId());
                System.out.print("  ");
                System.out.print(list[i].getName());
                System.out.print("  ");
                System.out.println();

            }
            else {
                System.out.print("null");
                System.out.print(", ");
            }
        }
    }
    public void remove(int index){
        list[index] = null;
    }
    public void clear(){
        for (int i = 0; i < emptyHomeIndex; i++) {
            remove(i);
        }
    }
    public Boolean contains(Branch number){
        boolean isContains = false;
        for (int i = 0; i < emptyHomeIndex; i++) {
            if (list[i] != null){
                if (list[i] == number) {
                    isContains = true;
                    break;
                }
            }
            else
                continue;
        }
        return isContains;
    }
}
