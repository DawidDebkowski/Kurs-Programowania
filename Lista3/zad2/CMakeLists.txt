cmake_minimum_required(VERSION 3.0.0)
project(Figury VERSION 0.1.0 LANGUAGES C CXX)

include(CTest)
enable_testing()

include_directories("*.hpp")
set(sources, "*.cpp")
add_executable(Figury main.cpp ${sources})

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
