#pragma once

#include "stdexcept"

namespace ErrorsSpace {

    class IncompatibleWorkersTypes : public std::runtime_error {
    public:
        explicit IncompatibleWorkersTypes(char const* const message) noexcept
            : runtime_error(message) {}
    };
}