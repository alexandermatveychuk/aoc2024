use std::env;
use std::fs::File;
use std::io::{BufRead, BufReader};
use anyhow::{Result};

pub fn read_input(name: &str) -> Result<Vec<String>> {
    let cwd = env::current_dir()?;
    let path = cwd.join(format!("../inputs/{}.txt", name)).canonicalize()?;
    let file = File::open(path)?;
    let lines = BufReader::new(file).lines();
    Ok(lines.map(|l| l.unwrap()).collect())
}