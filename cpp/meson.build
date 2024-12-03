project('cpp', 'cpp',
        version : '1.0.0',
        default_options : ['warning_level=3', 'cpp_std=c++20'])

cpp = executable('cpp', 'main.cpp', 'utils.hpp', 'day01.hpp', install : true)

test('test', cpp)