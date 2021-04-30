package list;

public class SingleLink<T extends Comparable<T>> implements Link<T> {
    private static class Entry<T> {
        private T value;
        private Entry<T> next;

        public Entry(T value) {
            this.value = value;
        }
    }
    private int size;
    private Entry<T> headEntry;
    private Entry<T> tailEntry;
    public SingleLink(){
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return headEntry==null;
    }

    @Override
    public boolean contains(T value) {
        for(Entry<T> p = headEntry;p!=null;p=p.next){
            if(p.value.compareTo(value) == 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addHead(T value) {

        Entry<T> newEntry = new Entry<>(value);
        if(isEmpty()){
            headEntry = newEntry;
            tailEntry = newEntry;
        }else{//链表不为空
            newEntry.next = headEntry;//新节点的下一个指向原来的头部节点
            headEntry = newEntry;//
        }
        size++;
    }

    @Override
    public void addTail(T value) {
        Entry<T> newEntry = new Entry<>(value);
        if(isEmpty()){
            headEntry = newEntry;
            tailEntry = newEntry;
        }else{
            tailEntry.next = newEntry;
            tailEntry = newEntry;
        }
        size++;

    }

    @Override
    public void removeHead() {
        if(isEmpty()){
            return;
        }
        headEntry.value = null;//放止内存泄漏
        headEntry = headEntry.next;
        if(headEntry == null){
        }
        size--;
    }

    @Override
    public void removeTail() {
        if(isEmpty()){
            return;
        }
        tailEntry.value = null;
        // 如果当前链表中只有一个节点
        if(size == 1){
            headEntry = null;
            tailEntry = null;
        }else {//当前节点个数>=2
            //找尾巴前驱节点
            Entry<T> p = null;
            for (p = headEntry; p.next!=tailEntry; p = p.next) {
                ;
            }
            // 前驱节点的next = null;
            p.next = null;
            // 更新tailEntry
            tailEntry = p;
        }

        size--;
    }

    @Override
    public void removeValue(T value) {
        if(isEmpty()){
            return;
        }
        //链表中只有一个节点
        if(size == 1){
            removeHead();
        }else {//当前节点个数>=2
            //如果待删节点是头节点
            if(headEntry.value.compareTo(value) == 0){
                removeHead();
            }else if(tailEntry.value.compareTo(value) == 0){
                removeTail();
            }else {
                //如果待删节点是尾节点
                for (Entry<T> p = headEntry; p.next != null; p = p.next) {
                    if (p.next.value.compareTo(value) == 0) {
                        p.next.value = null;
                        p.next = p.next.next;
                        size--;
                    }
                }
            }
        }
    }
    public  void show(){
        for(Entry<T> p = headEntry;p!=null;p=p.next){
            System.out.print(p.value);
        }
    }



}
