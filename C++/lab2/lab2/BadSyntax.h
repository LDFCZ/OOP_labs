#pragma once

#include "stdexcept"

namespace ErrorsSpace {

    class BadSyntax : public std::runtime_error {
    public:
        explicit BadSyntax(char const* const message) noexcept
            : runtime_error(message) {}
    };
}