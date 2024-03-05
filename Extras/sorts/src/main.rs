fn main() {
    println!("Hello, world!");



    let mut v = [0 ,4 , 5, 1, 2, 34, 2,6 ,3 ,1 ,1].to_vec();
    insertion_sort(&mut v);
    println!("{v:?}");
}

fn selection_sort<T: Ord>(arr: &mut Vec<T>) {
    let len = arr.len();
    for i in 0..len {
        let mut min_index = i;
        for j in (i+1)..len {
            if arr[j] < arr[min_index] {
                min_index = j;
            }
        }
        arr.swap(i, min_index);
    }
}

fn bubble_sort<T: Ord>(arr: &mut Vec<T>) {
    let len = arr.len();
    let mut swapped;

    loop {
        swapped = false;

        for i in 0..len - 1 {
            if arr[i] > arr[i + 1] {
                arr.swap(i, i + 1);
                swapped = true;
            }
        }

        if !swapped {
            break;
        }
    }
}



fn insertion_sort<T: Ord + Clone>(arr: &mut Vec<T>) {
    let n = arr.len();

    for i in 1..n {
        let val = arr[i].clone();
        let mut j = i;

        while j > 0 && arr[j - 1] > val {
            arr[j] = arr[j - 1].clone();
            j -= 1;
        }

        arr[j] = val;
    }
}
