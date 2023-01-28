package ua.com.alevel;

import java.util.*;
import java.util.function.Consumer;

public class MatList<E extends Number> implements List<E> {

    private static final int DEFAULT_LENGTH = 10;
    public Object[] elementData;
    private int size;

    private final Object[] EMPTY_ARRAY_WITH_DEFAULT_LENGTH = new Object[DEFAULT_LENGTH];

    public MatList() {
        this.elementData = EMPTY_ARRAY_WITH_DEFAULT_LENGTH;
    }

    public MatList(E[]... numbers) {
        for (E[] number : numbers)
            this.elementData = number;
    }

    public MatList(MatList... numbers) {
        Object[] array = Arrays.stream(numbers).toArray();
        if ((size = array.length) != 0) {
            elementData = array;
        } else {
            elementData = EMPTY_ARRAY_WITH_DEFAULT_LENGTH;
        }
    }


    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        add((E) null);
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
    }

    @Override
    public boolean add(E e) {
        if (size == elementData.length) {
            Object[] newArray = new Object[(int) (size * 1.5)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
        elementData[size] = e;
        size++;
        return true;
    }

    public void add(E... e) {
        for (E newArray : e) {
            add(newArray);
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] arrayAdd = c.toArray();
        if (arrayAdd.length == 0)
            return false;
        for (int i = 0; i < arrayAdd.length; i++) {
            add((E) arrayAdd[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(index);
        Object[] arrayAdd = c.toArray();
        if (arrayAdd.length == 0)
            return false;
        Object[] newArray = new Object[(int) (size + arrayAdd.length + 1)];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
        Object[] tempArray = Arrays.copyOfRange(elementData, index, size);
        for (int i = index; i < size; i++) {
            elementData[i] = null;
        }
        System.arraycopy(arrayAdd, 0, elementData, index, arrayAdd.length);
        System.arraycopy(tempArray, 0, elementData, index + arrayAdd.length, tempArray.length);
        size = size + arrayAdd.length;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object v : c.toArray())
            if (!contains(v))
                return false;
        return true;
    }

    @Override
    public E get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        E tempValue = (E) elementData[index];
        elementData[index] = element;
        return tempValue;
    }

    @Override
    public int indexOf(Object o) {
        Object[] indexOb = elementData;
        for (int i = 0; i < size; i++) {
            if (indexOb[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Object[] arrayIndex = elementData;
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arrayIndex[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(arrayIndex[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Object[] array = Arrays.copyOfRange(elementData, fromIndex, toIndex);
        MatList newArray = new MatList();
        for (int i = 0; i < array.length; i++) {
            newArray.add((Number) array[i]);
        }
        return newArray;
    }

    public void join(MatList... ml) {
        for (MatList joinList : ml) {
            Object[] newArray = joinList.toArray();
            for (int i = 0; i < newArray.length; i++) {
                add((E) newArray[i]);
            }
        }
    }

    public void intersection(MatList... ml) {
        for (MatList joinList : ml) {
            Object[] temp = elementData;
            for (int i = 0; i < temp.length; i++) {
                if (!joinList.contains(temp[i])) {
                    temp[i] = null;
                }
            }
            clear();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    add((E) temp[i]);
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        Object[] remArray = elementData;
        for (int i = 0; i < size; i++) {
            if (remArray[i].equals(o)) {
                remArray[i] = null;
                if ((size - 1) > i)
                    System.arraycopy(remArray, i + 1, remArray, i, (size - 1) - i);
                remArray[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        Object[] arrayRem = elementData;
        E elemRem = (E) elementData[index];
        if ((size - 1) > index)
            System.arraycopy(arrayRem, index + 1, arrayRem, index, (size - 1) - index);
        arrayRem[size - 1] = null;
        size--;
        return elemRem;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] remArray = c.toArray();
        Object[] elementData = this.elementData;
        int index = 0;
        for (int i = 0; i < remArray.length; i++) {
            for (int j = 0; j < elementData.length; j++)
                if (remArray[i].equals(elementData[j])) {
                    remove(elementData[j]);
                }
            this.elementData = elementData;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] temp = new Object[c.size() - 1];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(elementData[i])) {
                temp[k] = elementData[i];
                k++;
                size--;
            }
        }
        elementData = temp;
        return true;
    }

    @Override
    public void clear() {
        elementData = EMPTY_ARRAY_WITH_DEFAULT_LENGTH;
        size = 0;
    }

    public void sortDesc() {
        Object[] s = this.toArray();
        Collections.sort(Arrays.asList(s), Collections.reverseOrder());
        this.elementData = Arrays.copyOf(s, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        Object[] s = this.toArray(firstIndex, lastIndex);
        Collections.sort(Arrays.asList(s), Collections.reverseOrder());
        int k = 0;
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            this.set(i, (E) s[k]);
            k++;
        }
    }

    public void sortDesc(E value) {
        Object[] s = new Object[size];
        s = this.toArray();
        int fromIndex = this.indexOf(value);
        int toIndex = this.size() - 1;
        this.sortDesc(fromIndex, toIndex);
    }

    public void sortAsc() {
        Object[] s = this.toArray();
        Arrays.sort(s);
        this.elementData = Arrays.copyOf(s, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        Object[] sortArray = this.toArray(firstIndex, lastIndex);
        Arrays.sort(sortArray);
        int k = 0;
        for (int i = firstIndex; i < lastIndex + 1; i++) {
            this.set(i, (E) sortArray[k]);
            k++;
        }
    }

    public void sortAsc(E value) {
        Object[] s = new Number[size];
        s = this.toArray();
        int fromIndex = this.indexOf(value);
        int toIndex = this.size() - 1;
        this.sortAsc(fromIndex, toIndex);
    }

    public Number getMax() {
        sortDesc();
        return get(0);
    }

    public Number getMin() {
        sortAsc();
        return get(0);
    }

    public Number getAverage() {
        double[] numbers = new double[size];

        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] instanceof Number) {
                numbers[i] = ((Number) elementData[i]).doubleValue();
            }
        }
        return Arrays.stream(numbers).average().getAsDouble();
    }

    public Number getMedian() {
        sortAsc();
        return get(size / 2);
    }

    public Object[] toArray(int firstIndex, int lastIndex) {
        Object[] s = new Object[lastIndex - firstIndex + 1];
        for (int i = 0; i < s.length; i++) {

            if (elementData[firstIndex + i] instanceof Number) {
                s[i] = elementData[firstIndex + i];
            }
        }
        return s;
    }

    public MatList cut(int firstIndex, int lastIndex) {
        Object[] array = Arrays.copyOfRange(elementData, firstIndex, lastIndex);
        MatList newArray = new MatList();
        for (int i = 0; i < array.length; i++) {
            newArray.add((Number) array[i]);
        }
        return newArray;
    }

    public void clear(Number[] number) {
        removeAll(Arrays.stream(number).toList());
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        return new ListIter(index);
    }

    public class Iter implements Iterator<E> {
        int indexIter;
        int lastIndexIter;

        Iter() {
        }

        @Override
        public boolean hasNext() {
            return indexIter != size;
        }

        @Override
        public E next() {
            int i = indexIter;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MatList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            indexIter = i + 1;
            return (E) elementData[lastIndexIter = i];
        }

        @Override
        public void remove() {
            if (lastIndexIter < 0)
                throw new IllegalStateException();
            try {
                MatList.this.remove(lastIndexIter);
                indexIter = lastIndexIter;
                lastIndexIter = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            final int size = MatList.this.size;
            int i = indexIter;
            if (i < size) {
                final E[] es = (E[]) elementData;
                for (; i < size; i++)
                    action.accept(es[i]);
                indexIter = i;
                lastIndexIter = i - 1;
            }
        }
    }

    private class ListIter extends MatList<E>.Iter implements ListIterator<E> {
        ListIter(int index) {
            super();
            indexIter = index;
        }

        public boolean hasPrevious() {
            return this.indexIter != 0;
        }

        public int nextIndex() {
            return this.indexIter;
        }

        public int previousIndex() {
            return this.indexIter - 1;
        }

        public E previous() {
            int i = this.indexIter - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.indexIter = i;
                    return (E) elementData[this.lastIndexIter = i];
                }
            }
        }

        public void set(E e) {
            if (this.lastIndexIter < 0) {
                throw new IllegalStateException();
            } else {
                try {
                    MatList.this.set(this.lastIndexIter, e);
                } catch (IndexOutOfBoundsException var3) {
                    // ну брось тут что-нить)))
                }
            }
        }

        public void add(E e) {
            try {
                int i = this.indexIter;
                MatList.this.add(i, e);
                this.indexIter = i + 1;
                this.lastIndexIter = -1;
            } catch (IndexOutOfBoundsException var3) {
                // ну брось тут что-нить)))
            }
        }
    }
}