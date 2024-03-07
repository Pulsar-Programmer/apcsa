fn main() {
    println!("Hello, world!");



    let mut v = [0 ,4 , 5, 1, 2, 34, 2,6 ,3 ,1 ,1].to_vec();
    insertion_sort(&mut v);
    println!("{v:?}");
}

fn selection_sort<T: Ord>(arr: &mut Vec<T>) {
    let len = arr.len();

    //We loop over each index to find the next place to put the smallest element.
    for i in 0..len {
        //We start by assuming we are at the minimum index.
        let mut min_index = i;
        //We commit a rolling check forward
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
        //We create a rolling check between each element and its next element.
        for i in 0..len - 1 {
            //If they are out of order, we put them in order and confirm our swap.
            if arr[i] > arr[i + 1] {
                arr.swap(i, i + 1);
                swapped = true;
            }
        }
        //If we haven't swapped at all during the rolling check, we can conclude the vec is sorted.
        if !swapped {
            break;
        }
    }
}



fn insertion_sort<T: Ord + Clone>(arr: &mut Vec<T>) {
    let n = arr.len();
    
    //We start by reserving the first index for the newly built array, while moving forward.
    for i in 1..n {
        //We get our value in our current index.
        let val = arr[i].clone();
        let mut j = i;

        while j > 0 && arr[j - 1] > val {
            arr[j] = arr[j - 1].clone();
            j -= 1;
        }

        arr[j] = val;
    }
}
