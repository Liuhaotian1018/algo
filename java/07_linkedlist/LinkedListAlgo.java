package linkedlist;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 *
 * Author: Zheng
 */
public class LinkedListAlgo {

  // 单链表反转
  public static Node reverse(Node list) {
    Node curr = list, pre = null;
    while (curr != null) {
      //1.先保存一下下一个节点
      Node next = curr.next;
     //2.将当前节点的next指针指向前一节点
      curr.next = pre;
     //3.将当前节点变为前一个节点
      pre = curr;
     //4.将下一个节点变为当前节点，重复上述操作，直到最后一个节点
      curr = next;
    }
	  //5.最后返回当前节点，即原来的最后一个节点
    return pre;
  }

  // 检测环------快慢指针法------
  public static boolean checkCircle(Node list) {
    if (list == null) return false;

    Node fast = list.next;
    Node slow = list;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;

      if (slow == fast) return true;
    }

    return false;
  }


    // 有序链表合并 Leetcode 21 
    /**
    将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	示例：
	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
 	* Definition for singly-linked list.
 	* public class ListNode {
 	*     int val;
 	*     ListNode next;
 	*     ListNode(int x) { val = x; }
 	* }
	*/
   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode soldier = new ListNode(0); //利用哨兵结点简化实现难度 技巧三
        ListNode p = soldier;
        
        while ( l1 != null && l2 != null ){ 
            if ( l1.val < l2.val ){
                p.next = l1;
                l1 = l1.next;
            }
            else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        
        if (l1 != null) { p.next = l1; }
        if (l2 != null) { p.next = l2; }
        return soldier.next;   
    }


  // 删除倒数第K个结点 注意null的判断
  public static Node deleteLastKth(Node list, int k) {
    Node fast = list;
    int i = 1;
    while (fast != null && i < k) {
      fast = fast.next;
      ++i;
    }
    //防止出现 k 大于 链表的长度的情况，此时fast == null
    if (fast == null) return list;

    Node slow = list;
    Node prev = null;
    while (fast.next != null) {//将fast指到最后一个节点
      fast = fast.next;
      prev = slow;//pre和fast差了k个节点
      slow = slow.next;//slow最终指向的节点就是要被删除的
    }

    if (prev == null) {//防止出现链表长度=n，k=n，即删除第一个节点的情况，此时pre还是null
      list = list.next;
    } else {
      prev.next = prev.next.next;
    }
    return list;
  }

  // 求中间结点 快慢指针 举个简单的例子，特别注意当链表长度是奇数时
  public static Node findMiddleNode(Node list) {
    if (list == null) return null;

    Node fast = list;
    Node slow = list;

    while (fast != null && fast.next != null) {//特别注意当链表长度是奇数时
      fast = fast.next.next;
      slow = slow.next;
    }

    return slow;
  }

  public static void printAll(Node list) {
    Node p = list;
    while (p != null) {
      System.out.print(p.data + " ");
      p = p.next;
    }
    System.out.println();
  }

  public static Node createNode(int value) {
    return new Node(value, null);
  }

  public static class Node {
    private int data;
    private Node next;

    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }

    public int getData() {
      return data;
    }
  }

}
