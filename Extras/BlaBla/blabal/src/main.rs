fn main() {
    // println!("Hello, world!");
    // println!("{}", blabla("hello1235465768i"));

    
}


fn blabla(st: impl ToString) -> String{
    let mut st = st.to_string();
    let len = st.len();
    st.clear();
    for i in 0..len{
        st.push(if i % 3 == 0{'b'} else if i % 3==1 {'l'} else{ 'a'}); 
    }
    st
}


fn blabla2(st: impl ToString) -> String{
    let mut st = st.to_string();
    let bla = "bla".as_bytes();
    let len = st.len();
    st.clear();
    for i in 0..len{
        st.push(bla[i % 3] as char); 
    }
    st
}

