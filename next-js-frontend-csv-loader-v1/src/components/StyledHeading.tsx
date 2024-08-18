// src/components/StyledHeading.tsx
"use client"; // Ensure this is a Client Component

import React from "react";

interface StyledHeadingProps {
    text: string;
    className?: string;
}

const StyledHeading: React.FC<StyledHeadingProps> = ({ text, className = '' }) => {

    return (
        <h1 className={`flex flex-wrap text-5xl font-bold pb-10 ${className}`}>
            {text}
        </h1>
    );
};

export default StyledHeading;