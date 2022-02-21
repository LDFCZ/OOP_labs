#pragma once

#include "Creator.h"
#include "SortWorker.h"

namespace MainWorkerCreators {

    class SortCreator : public Interfaces::Creator {
    public:
        Interfaces::Worker* create_worker(std::vector<std::wstring>&, MainContext::Context&) const override;
    };
}
