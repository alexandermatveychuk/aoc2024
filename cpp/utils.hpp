#ifndef UTILS_HPP
#define UTILS_HPP

#include <filesystem>
#include <fstream>
#include <iostream>
#include <vector>

std::ifstream read_input(const std::string& name) {
    const auto dir = std::filesystem::current_path().parent_path().parent_path().append("inputs");
    const auto file = dir / (name + ".txt");
    return std::ifstream{file};
}

std::vector<std::string> read_lines(const std::string& name) {
    if (auto ifs = read_input(name)) {
        std::vector<std::string> lines;
        std::string line;
        while (std::getline(ifs, line)) {
            lines.push_back(line);
        }
        ifs.close();
        return lines;
    }
    std::cerr << "Could not open input " << name << " for reading." << std::endl;
    return {};
}

#endif  // UTILS_HPP
