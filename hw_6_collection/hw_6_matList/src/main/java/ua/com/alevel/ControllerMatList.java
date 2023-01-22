package ua.com.alevel;

public class ControllerMatList {

    public void start() {
        MatList<Integer> intList = new MatList<>();
        MatList<Integer> integerList = new MatList<>();
        MatList<Integer> intLists = new MatList<>();
        MatList<Integer> forSort = new MatList<>();
        System.out.println();
        System.out.println("Let`s test MatList");
        System.out.println();
        System.out.println("Testing add methods");
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean add(E e)");
        intList.add(100);
        for (Integer intLister : intList) {
            System.out.println("Result : " + intLister);
        }
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void add(E... e)");
        intList.add(101, 102, 103, 104, 105, 106, 107, 108, 109, 110);
        System.out.print("Result : ");
        for (Integer intLister : intList) {
            System.out.print(" " + intLister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void add(int index, E element)");
        intList.add(5, 200);
        System.out.print("Result add element 200 in index 5 position: ");
        for (Integer intLister : intList) {
            System.out.print(" " + intLister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean addAll(Collection<? extends E> c)");
        intLists.add(201, 202, 203, 204, 205, 206, 207, 208, 209);
        intList.addAll(intLists);
        System.out.print("Result add 9 elements to exist list: ");
        for (Integer intLister : intList) {
            System.out.print(" " + intLister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean addAll(int index, Collection<? extends E> c)");
        integerList.add(301, 302, 303, 304, 104);
        intList.addAll(10, integerList);
        System.out.print("Result add 4 elements to position 10: ");
        for (Integer intLister : intList) {
            System.out.print(" " + intLister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public int size()");
        System.out.println("Result : size list intList is " + intList.size());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean isEmpty()");
        System.out.println("Result : is list intList empty  - " + intList.isEmpty());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean contains(Object o)");
        System.out.println("Result : is list intList contains element 200  - " + intList.contains(200));
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public Object[] toArray()");
        System.out.println("Result : new array has all elements intList ");
        Object[] newArray = intList.toArray();
        for (Object newArr : newArray)
            System.out.print(" " + newArr);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing containsAll(Collection<?> c)");
        System.out.println("Result : is list intList contains element from intLists  - " + intList.containsAll(intLists));
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public E get(int index)");
        System.out.println("Result : element with index 7  - " + intList.get(7));
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public E set(int index, E element)");
        System.out.println("Result : set element 404 to index 5  - " + intList.set(5, 404));
        for (Integer intLister : intList) {
            System.out.print(" " + intLister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public int indexOf(Object o)");
        System.out.println("Result : index of element 303  - " + intList.indexOf(303));
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public int lastIndexOf(Object o)");
        System.out.println("Result : last index of element 104  is - " + intList.lastIndexOf(104));
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public List<E> subList(int fromIndex, int toIndex)");
        System.out.println("Result : new sublist newList contains - ");
        MatList<Integer> newList = (MatList<Integer>) intList.subList(3, 7);
        for (Integer lister : newList) {
            System.out.print(" " + lister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void join(MatList... ml)");
        System.out.println("Result : joined list  contain newList and intLists - ");
        newList.join(intLists);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void intersection(MatList... ml)");
        System.out.println("Result : intersected list contain same elements newList and intLists - ");
        newList.intersection(intLists);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean remove(Object o)");
        System.out.println("Result : remove element 203 from list - ");
        Integer number = 203;
        newList.remove(number);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public E remove(int index)");
        System.out.println("Result : remove element of index 3 from list - ");
        newList.remove(3);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean removeAll(Collection<?> c)");
        System.out.println("Result : remove all elements same with  newOneList from list - ");
        MatList<Integer> newOneList = new MatList<>();
        newOneList.add(202, 204, 206);
        newList.removeAll(newOneList);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public boolean retainAll(Collection<?> c))");
        System.out.println("Result : retain all elements same with newOneList - ");
        newOneList.add(207, 208, 206);
        newList.retainAll(newOneList);
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void clear()");
        System.out.println("Result : remove all elements from list - ");
        newList.clear();
        for (Integer intLister : newList)
            System.out.print(" " + intLister);
        System.out.println("clear!");
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortDesc()");
        System.out.println("Result : descending sort list - ");
        forSort.add(23, 56, 34, 786, 3534, 8, 2444, 456, 133, 456, 2);
        forSort.sortDesc();
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortAsc()");
        System.out.println("Result : ascending sort list - ");
        forSort.sortAsc();
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortDesc(int firstIndex, int lastIndex)");
        System.out.println("Result : descending sort list from index 2 to 6 - ");
        forSort.sortDesc(2, 6);
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortAsc(int firstIndex, int lastIndex)");
        System.out.println("Result : ascending sort list from index 2 to 6 - ");
        forSort.sortAsc(2, 6);
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortDesc(E value)");
        System.out.println("Result : descending sort list from element 34 - ");
        forSort.sortDesc(34);
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void sortAsc(E value)");
        System.out.println("Result : ascending sort list from element 456 - ");
        forSort.sortAsc(456);
        for (Integer intLister : forSort)
            System.out.print(" " + intLister);
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public Number getMax()");
        System.out.println("Result : max value is - " + forSort.getMax());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public Number getMin()");
        System.out.println("Result : min value is - " + forSort.getMin());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public Number getAverage()");
        System.out.println("Result : average value is - " + forSort.getAverage());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public Number getMedian()");
        System.out.println("Result : median value is - " + forSort.getMedian());
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public MatList cut(int firstIndex, int lastIndex)");
        System.out.println("Result : new MatList with elements from 3 to 7 index - ");
        MatList<Integer> cutList = (MatList<Integer>) forSort.cut(3, 7);
        for (Integer lister : cutList) {
            System.out.print(" " + lister);
        }
        System.out.println();
        System.out.println("_______________________________________________________________");
        System.out.println("Testing public void clear(Number[] number)");
        System.out.println("Result : clear elements same with this Number array - ");
        Number[] arrayInt = {102, 103, 104};
        intList.clear(arrayInt);
        for (Integer lister : intList) {
            System.out.print(" " + lister);
        }

    }
}
