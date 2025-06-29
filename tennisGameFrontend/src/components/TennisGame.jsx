import React, { useState } from "react";

function TennisScore() {
    const [sequence, setSequence] = useState("");
    const [scores, setScores] = useState([]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("/api/tennis/play", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ sequence }),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log("Response:", data);
            setScores(data);
        } catch (err) {
            console.error("Fetch error:", err.message);
            // Optionally display error to user
        }
    };

    // const handleSubmit = async (e) => {
    //     e.preventDefault();
    //
    //     // const response = await fetch("/api/tennis/play", {
    //     //     method: "POST",
    //     //     headers: { "Content-Type": "text/plain" },
    //     //     body: sequence,
    //     // });
    //     fetch("/api/tennis/play", {
    //         method: "POST",
    //         headers: {
    //             "Content-Type": "application/json",
    //         },
    //         body: JSON.stringify({ sequence }),
    //     })
    //         .then(res => {
    //             if (!res.ok) {
    //                 throw new Error(`HTTP error! Status: ${res.status}`);
    //             }
    //             return res.json();
    //         })
    //         .then(data => {
    //             console.log("Response:", data);
    //         })
    //         .catch(err => {
    //             console.error("Fetch error:", err.message);
    //         });
    //
    //     const data = await response.json();
    //     setScores(data);
    // };

    return (
        <div>
            <h1>Tennis Score Kata</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={sequence}
                    onChange={e => setSequence(e.target.value.toUpperCase().replace(/[^AB]/g,""))}
                    placeholder="Entrez la séquence, ex: ABABAA" />
                <button type="submit">Envoyer</button>
            </form>
            <div>
                {scores.map((score, idx) => (
                    <div key={idx}>{score}</div>
                ))}
            </div>
        </div>
    );
}

export default TennisScore;