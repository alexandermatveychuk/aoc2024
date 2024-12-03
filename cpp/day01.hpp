#ifndef DAY01_HPP
#define DAY01_HPP

#include <iostream>
#include <vector>
#include "utils.hpp"

std::vector<std::pair<int, int>> parse_input();

int day01part1(const std::vector<std::pair<int, int>>& input) {

    std::vector<int> left_column, right_column;
    for (const auto& [fst, snd] : input) {
        left_column.push_back(fst);
        right_column.push_back(snd);
    }
    std::ranges::sort(left_column);
    std::ranges::sort(right_column);
    int result = 0;
    auto left_it = left_column.begin();
    auto right_it = right_column.begin();
    while (left_it != left_column.end() && right_it != right_column.end()) {
        result += abs(*left_it++ - *right_it++);
    }
    return result;
}

void day01main() {
    const auto input = parse_input();
    std::cout << day01part1(input) << std::endl;
}

std::vector<std::pair<int, int>> parse_input() {
    if (auto input = read_input("input01")) {
        std::vector<std::pair<int, int>> pairs;
        std::string first, second;
        while (!input.eof()) {
            input >> first >> second;
            pairs.emplace_back(std::stoi(first), std::stoi(second));
        }
        return pairs;
    }
    std::cerr << "Cannot read input01" << std::endl;
    return {};
}

#endif  // DAY01_HPP
