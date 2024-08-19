// src/components/JobRunInput.tsx
"use client"; // Ensure this is a Client Component

import React from 'react';
import {Button} from "@nextui-org/react";
import {Card} from "@nextui-org/card";
import JobRunService from "@/lib/JobRunService";

const JobRunInput: React.FC = () => {

    const handleUpload = async () => {
        await JobRunService.runJob()
    };

    return (
        <div className="flex flex-col justify-center ">
            <Card className="grid grid-cols-5 gap-4 justify-center p-5 bg-black/40 border-none" isBlurred>
                <Button
                    onClick={handleUpload}
                    color={"primary"}
                    radius={"sm"}
                    className="col-span-5"
                >
                    Run ETL Job!
                </Button>

            </Card>
        </div>
    );
};

export default JobRunInput;