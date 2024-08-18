import Image from "next/image";
import FileInput from "@/components/FileInput";
import HomePage from "@/components/HomePage";

export default function Home() {
  return (
    <main className="flex min-h-svh flex-col items-center justify-between p-24 bg-gradient-to-br from-slate-300 to-slate-500">
        <HomePage></HomePage>
    </main>
  );
}
