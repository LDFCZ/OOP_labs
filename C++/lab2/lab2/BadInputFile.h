#pragma once

#include "stdexcept"

namespace ErrorsSpace {

    class BadInputFile : public std::runtime_error {
    public:
        explicit BadInputFile(char const* const message) noexcept
            : runtime_error(message) {}
    };
}