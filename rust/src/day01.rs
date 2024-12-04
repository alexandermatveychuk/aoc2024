use crate::utils::read_input;
use std::iter::zip;
use std::str::FromStr;

pub fn day01_part1<T: AsRef<str>>(input: &[T]) -> i32 {
    let (mut left_column, mut right_column): (Vec<i32>, Vec<i32>) = input
        .iter()
        .map(|line| {
            let numbers = line.as_ref()
                .split(' ')
                .filter(|s| !s.is_empty())
                .map(|s| i32::from_str(s))
                .filter_map(Result::ok)
                .collect::<Vec<i32>>();
            match &numbers[..] {
                &[first, second] => (first, second),
                _ => unreachable!("More than two numbers!"),
            }
        })
        .unzip();
    left_column.sort();
    right_column.sort();
    zip(left_column, right_column)
        .map(|(first, second)| (first - second).abs())
        .fold(0, |acc, item| acc + item)
}

pub fn day01_main() {
    let input = read_input("input01").unwrap();
    println!("Part 1: {}", day01_part1(&input));
}
