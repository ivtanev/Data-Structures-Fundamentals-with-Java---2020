import java.util.Iterator;

public class ReverseddList<E> implements Iterable<E> {
    private  final int Def_Capacity = 2;
    private int _size;

    private Object[] internArr;

    public ReverseddList()
    {
        this.internArr = new Object[Def_Capacity];
        this._size = 0;
    }

    public void add(E element) {
        CheckCapacity();
        this.internArr[this._size] = element;
        this._size++;
    }


    public int size() {
        return this._size;
    }


    public int capacity() {
        return this.internArr.length;
    }


    public E get(int index) {
        if (this._size <=0 || !(IsValidIndex(index))){
            return null;
        }

        int realIndex = GetRealIndex(index);
        return GetAt(realIndex);
    }


    public E removeAt(int index) {

        if(!(IsValidIndex(index)) || this._size <= 0)
        {
            return null;
        }
        int realIndex = GetRealIndex(index);
        E element = GetAt(realIndex);
        RemoveElement(realIndex);
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>()  {
            private int current = _size -1;
            @Override
            public boolean hasNext() {
                return current>=0;
            }

            @Override
            public E next() {
                return GetAt(current--);
            }
        };
    }

    private void CheckCapacity() {
        if (capacity() == this._size) {
            GrowInnerArray();
        }
    }

    private E GetAt(int realIndex){
        return (E)this.internArr[realIndex];
    }

    private int GetRealIndex(int index){
        return ((this._size -1)-index);
    }

    private void GrowInnerArray(){
        Object[] temp = new Object[(capacity()*2)];
        for (int i = 0; i < this._size; i++) {
            temp[i] = this.internArr[i];
        }
        this.internArr = temp;
    }

    private boolean IsValidIndex(int index){
        if(index >= this._size || index < 0){
            return false;
        }
        return true;
    }

    private void RemoveElement(int realIndex){
        for (int i = realIndex; i < this._size; i++) {
            this.internArr[i] = this.internArr[i+1];
        }
        this.internArr[_size -1] = null;
        this._size--;
    }
}
