use std::cell::{RefCell, Cell};

fn main() {
    println!("Hello, world!");

    let mut vec = vec![1, 2, 3, 4];
    println!("{vec:?}");
    for i in vec.iter_mut(){
        *i += 5;
    }
    println!("{vec:?}");

    let vec = vec.into_iter().map(|x|{RefCell::new(x)}).collect::<Vec<_>>();
    for i in vec.iter(){
        *i.borrow_mut() += 5;
    }
    let vec = vec.into_iter().map(|x|{x.into_inner()}).collect::<Vec<_>>();
    println!("{vec:?}");




    // println!("{:?}", flip_vecs(vec![vec![1, 2, 3]]));

}

// fn make_square<T: Default>(vec: Vec<Vec<T>>) -> Vec<Vec<T>>{
//     let mut max_len = 0;
//     vec.into_iter().for_each(move|a|max_len = max_len.max(a.len()));



//     // todo!();
    
// }
fn max_len<T>(vec: &Vec<Vec<T>>) -> usize{
    let mut max_len = vec.len();
    vec.iter().for_each(|a|max_len = max_len.max(a.len()));
    max_len
}

fn flip_vecs<T: Default>(vec: Vec<Vec<T>>) -> Vec<Vec<T>>{
    let square_length = max_len(&vec);
    let mut new: Vec<Vec<T>> = {
        let mut a = Vec::new();
        for i in 0..square_length{
            a.push(with_len(square_length));
        }
        a
    };
    for (i, v) in vec.into_iter().enumerate(){
        for (j, elem) in v.into_iter().enumerate(){
            new[j][i] = elem;
        }
    }
    new
}

fn with_len<T: Default>(len: usize) -> Vec<T>{
    let mut vec = Vec::new();
    for i in 0..len{
        vec.push(Default::default());
    }
    vec
}