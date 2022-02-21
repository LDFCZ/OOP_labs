#pragma once

#include <vector>

#include "Context.h"

namespace Interfaces {

    class Creator {
    public:
        // We will give parsed command line to new worker
        virtual Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const = 0;
    };
}