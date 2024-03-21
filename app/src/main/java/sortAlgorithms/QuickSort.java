package sortAlgorithms;

public class QuickSort extends SortClass {
    private int pivot;
    private float floatArray[];
    private String typeOfData;


    protected QuickSort(int size) {
        super(size);
    }

    protected QuickSort(String fileName) throws Exception {
        super(fileName);
    }

    protected void sortAlgorithm(int left, int right) {
        int pivot = array[this.pivot];
    }

    public int getPivot() {
        return pivot;
    }

    public void setPivot(int pivot) {
        this.pivot = pivot;
    }

    public String getTypeOfData() {
        return typeOfData;
    }

    public void setTypeOfData(String typeOfData) {
        this.typeOfData = typeOfData;
    }






}
