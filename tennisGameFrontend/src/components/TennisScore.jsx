import React, { useState } from "react";

function TennisScore() {
    const [sequence, setSequence] = useState("");
    const [scores, setScores] = useState([]);
    const API_BASE_URL = window._env_?.REACT_APP_API_BASE_URL || process.env.REACT_APP_API_BASE_URL;

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`${API_BASE_URL}/api/tennis/play`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: sequence,
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log("Response:", data);
            setScores(data);
        } catch (err) {
            console.error("Fetch error:", err.message);
        }
    };

    return (
        <div>
            <h1>Tennis Score Computer</h1>
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