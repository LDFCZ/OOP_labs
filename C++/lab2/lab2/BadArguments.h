#pragma once

#include "stdexcept"

namespace ErrorsSpace {

    class BadArguments : public std::runtime_error {
    public:
        explicit BadArguments(char const* const message) noexcept
            : runtime_error(message) {}
    };
}